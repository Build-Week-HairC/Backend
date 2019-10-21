package com.lambdaschool.medcabinet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.medcabinet.models.APIStrain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableWebMvc
@EnableJpaAuditing
@SpringBootApplication
public class MedCabinetApplication
{
    private static final Logger logger = LoggerFactory.getLogger(MedCabinetApplication.class);
    private static boolean stop = false;

    @Autowired
    private static Environment env;

    private static void checkEnvironmentVariable(String envvar)
    {
        if (System.getenv(envvar) == null)
        {
            logger.error("Environment Variable " + envvar + " missing");
            stop = true;
        }
    }

//    ArrayList<String> jsonStringToArray(String jsonString) throws JSONException {
//
//        ArrayList<String> stringArray = new ArrayList<String>();
//
//        JSONArray jsonArray = new JSONArray(jsonString);
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//            stringArray.add(jsonArray.getString(i));
//        }
//
//        return stringArray;
//    }

    public static void main(String[] args) throws IOException
    {
        checkEnvironmentVariable("OAUTHCLIENTID");
        checkEnvironmentVariable("OAUTHCLIENTSECRET");

        if (!stop)
        {
            ApplicationContext ctx = SpringApplication.run(MedCabinetApplication.class,
                                                           args);

            DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean("dispatcherServlet");
            dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = "[{\"StrainID\":806,\"Strain\":\"fruity-chronic-juice\",\"Type\":\"indica\",\"Rating\":4.2,\"Effects\":\"relaxed,euphoric,happy,uplifted,creative\",\"Flavor\":\"citrus,grapefruit,tropical\",\"Description\":\"fruity chronic juice, bred by delicious seeds, is an indica-dominant cross of white widow and chronic that is short in stature\\u00a0but can stretch tall when given enough room for the roots to spread. it expresses itself with an array of citrus flavors, like orange and grapefruit, that give way to subtle undertones of pine and hash on exhale. the potent effects balance between physical relaxation and cerebral energy to create a well-rounded buzz that is both calming and creative.\\u00a0\"},{\"StrainID\":807,\"Strain\":\"fruity-pebbles\",\"Type\":\"hybrid\",\"Rating\":4.4,\"Effects\":\"happy,relaxed,uplifted,euphoric,giggly\",\"Flavor\":\"sweet,tropical,berry\",\"Description\":\"fruity pebbles (aka fruity pebbles og) by alien genetics was a limited-time offering from the breeder. this sweet hybrid takes genetics from green ribbon, granddaddy purple, and tahoe alien\\u00a0to create a tropical, berry flavor reminiscent of the cereal. the euphoric effects will keep you happy when you\\u2019re stressed and help you catch some sleep when faced with insomnia. sit back, relax, and pour yourself a bowl of fruity pebbles!\"},{\"StrainID\":808,\"Strain\":\"fruity-widow\",\"Type\":\"hybrid\",\"Rating\":4.9,\"Effects\":\"relaxed,sleepy,happy,hungry,talkative\",\"Flavor\":\"sweet,blueberry\",\"Description\":\"fruity widow by 710 genetics is a coupling of classic phenotypes to create a potent union of sweet terpenes and hybridized effects. 710 genetics combined the flavorful blueberry and the well-balanced, resinous white widow to create an instant classic. enjoy fruity widow throughout the day to enhance mood, combat depression, and spur creativity. \\u00a0\"},{\"StrainID\":1002,\"Strain\":\"heavy-duty-fruity\",\"Type\":\"hybrid\",\"Rating\":4.1,\"Effects\":\"relaxed,happy,sleepy,hungry,giggly\",\"Flavor\":\"sweet,citrus,earthy\",\"Description\":\"heavy duty fruity seems to have everything going for it: high yields, yummy flavor, and potency. t. h. seeds bred this west coast native from local (and locally named) favorites killing garberville, seattle big bud, and mendocino hash plant to create a multi-skilled strain. growers should be aware that plants do not always have classic seven-point leaves, but her unique beauty does not affect her output. heavy duty fruity also gives off a heavy duty sweet smell, so it may require management for indoor growers. what is not a concern is yield and bud size, both of which are consistently large with this strain, both indoors and out. plants usually flower between 8 and 9 weeks. depending on when this plant is harvested, her effects range from psychedelic and spacey to mellow relaxation.\"},{\"StrainID\":1776,\"Strain\":\"seattle-blue\",\"Type\":\"indica\",\"Rating\":4.5,\"Effects\":\"euphoric,relaxed,uplifted,happy,hungry\",\"Flavor\":\"sweet,blueberry,pungent\",\"Description\":\"the seattle blue strain of cannabis provides a strong euphoric body high. \\u00a0the sweet and fruity taste makes this a very pleasant indica.\"}]";

//        APIStrain[] list = mapper.readValue(json, APIStrain[].class);

//        List<APIStrain> list = Arrays.asList(new ObjectMapper().readValue(json, APIStrain.class));

//        List<APIStrain> list = new ObjectMapper().readValue(json, new TypeReference<List<APIStrain>>() {});

//        System.out.println("JSON array to Array objects...");
//        for (APIStrain strain : list) {
//            System.out.println(strain);
//        }

//        list.stream().forEach(x -> System.out.println(x));



    }

}
