package org.gbview.io.gbx.parser;

import org.gbview.io.FileParser;
import org.gbview.io.gbx.GbxFormat;
import org.gbview.io.gbx.definition.*;
import org.gbview.model.*;
import org.gbview.model.particle.Particle;
import org.gbview.model.type.ParticleType;
import org.gbview.model.util.Index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

/**
 * Parser for gbx format. Used with {@link org.gbview.io.gbx.GbxReader}
 */
public class GbxParser implements FileParser
{

    protected static final String DELIMITERS = "\\s+";

    private List<GbParticleDefinition> particleDefinitionList;
    private List<GbMoleculeDefinition> moleculeDefinitionList;
    private GbBoxDefinition boxDefinition;

    private Model model;

    public GbxParser()
    {
        moleculeDefinitionList = new ArrayList<GbMoleculeDefinition>();
        particleDefinitionList = new ArrayList<GbParticleDefinition>();

        model = new SimpleModel();
    }

    @Override
    public Model getModel() { return model;  }

    @Override
    public void parse(String line) throws IOException
    {
        if(isComment(line)) return;

        // split line into string tokens delimited by spaces
        String tokens[] = line.split(DELIMITERS);

        // metadata, i.e. molecule, particle, bond and box definition
        if(GbDefinitionType.contains(tokens[0]) || ParticleType.contains(tokens[0]))
        {
            parseDefinitions(tokens);
        }
        // if neither molecular, particle, bond or box, then it must be data
        else
        {
            parseData(tokens);
        }
    }

    protected boolean isComment(String line)
    {
        for(String key : GbxFormat.getAllowedCommentsKeyword())
        {
            if(line.startsWith(key))
            {
                return true;
            }
        }
        return false;
    }

    private void parseDefinitions(String[] tokens) throws IOException
    {
        GbDefinitionType definitionType;
        if((definitionType = GbDefinitionType.getType(tokens[0])) != null)
        {
            switch(definitionType)
            {
                case MOL:
                    this.moleculeDefinitionList.add(GbxMoleculeParser.parse(tokens));
                    break;
                case BOX:
                    this.boxDefinition = GbxBoxParser.parse(tokens);
                    this.model.setBox(this.boxDefinition.createBox());
                    break;
                case BOND:
                    throw new UnsupportedOperationException();
            }
        }

        // Particle definition are separate as there is more than one type of particle available.
        ParticleType particleType;
        if((particleType = ParticleType.parse(tokens[0])) != null)
        {
            this.particleDefinitionList.add(ParticleType.createParticleDefinition(particleType,tokens));
        }

    }

    private void parseData(String[] tokens)
    {
        for(GbParticleDefinition particleDefinition: this.particleDefinitionList)
        {
            if(tokens[0].equals(particleDefinition.getName()))
            {
                double px, py, pz;
                if (tokens.length == 7)
                {
                    px = Double.valueOf(tokens[1]);
                    py = Double.valueOf(tokens[3]);
                    pz = Double.valueOf(tokens[5]);
                }
                else if (tokens.length == 4) {
                    px = Double.valueOf(tokens[1]);
                    py = Double.valueOf(tokens[2]);
                    pz = Double.valueOf(tokens[3]);
                }
                else
                {
                    throw new RuntimeException("Wrong number of data point in a line");
                }

                // create particle from defined properties in GBX file
                Particle particle = particleDefinition.createParticle(px,py,pz);

                // add particle to model
                Index index = new Index(this.model.getParticles().size());
                this.model.addParticle(index, particle);

                // add index to particle definition
                particleDefinition.addIndex(index);
            }
        }
    }
}
