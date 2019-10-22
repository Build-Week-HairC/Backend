package com.lambdaschool.medcabinet.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.medcabinet.logging.Loggable;
import com.lambdaschool.medcabinet.models.APIStrain;
import com.lambdaschool.medcabinet.models.ResStrain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This can be removed from the base application. This controller contains
 * examples of how to handle other situations
 * <p>
 * > Reading another API
 * > Uploading a file into a db (in progress)
 * > Uploading a file into a file (in progress)
 * > sending an email from an endpoint (in progress)
 * > sending something via twilio from an endpoint (in progress)
 */

// allows us to get data from other APIs into our system

@Loggable
@RestController
@RequestMapping("/api")
public class APIsController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value = "/ds/{query}",
                produces = {"application/json"})
    public ResponseEntity<?> searchStrains(HttpServletRequest request, @PathVariable String query) throws IOException
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        // TODO -- restrict query to alphanumeric characters
        query = query.replaceAll("[\\s]", ",");
        String requestURL = "https://morning-badlands-32563.herokuapp.com/recommend/?" + query;

        ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<>()
        {
        };
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestURL, HttpMethod.GET, null, responseType);
        String jsonString = responseEntity.getBody().replace("\\","").replace("u00a0", "");
        jsonString = jsonString.substring(1, jsonString.length() - 1);

        ObjectMapper mapper = new ObjectMapper();
        List<APIStrain> list = mapper.readValue(jsonString, new TypeReference<List<APIStrain>>() {});

        List<ResStrain> resList = new ArrayList<>();
        for(APIStrain s : list)
        {
            ResStrain resStrain = new ResStrain();
            resStrain.setStrainid(s.getStrainID());
            resStrain.setStrain(s.getStrain());
            resStrain.setDescription(s.getDescription());
            resStrain.setType(s.getType());
            resStrain.setRating(s.getRating());

            List<String> effects = Arrays.asList(s.getEffects().split("\\s*,\\s*"));
            for(String e : effects)
            {
                resStrain.getEffects().add(e);
            }

            List<String> flavors = Arrays.asList(s.getFlavor().split("\\s*,\\s*"));
            for(String f : flavors)
            {
                resStrain.getFlavors().add(f);
            }

            resList.add(resStrain);
        }

        return new ResponseEntity<>(resList, HttpStatus.OK);
    }
}
