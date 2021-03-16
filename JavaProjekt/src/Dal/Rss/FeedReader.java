/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal.Rss;

import Model.Movie;
import Utilities.TextFormator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.net.ssl.HttpsURLConnection;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Fran
 */
public class FeedReader {

    private static final String[] RSS_URL = {"https://www.blitz-cinestar.hr/rss.aspx"};
    private static final int TIMEOUT = 10000;
    private static final String REQUEST_METHOD = "GET";


    public  List<Movie> parse() throws IOException, XMLStreamException {
         List<Movie> movies = new ArrayList<>();
         for (int i = 0; i < RSS_URL.length; i++) {
            HttpsURLConnection con = UrlConnectionFactory.getHttpsUrlConnection(RSS_URL[i], TIMEOUT, REQUEST_METHOD);
            XMLEventReader reader = ParserFactory.createStaxParser(con.getInputStream());

            int id = 1;

            Optional<TagType> tagType = Optional.empty();
            Movie movie = null;
            StartElement startElement = null;
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();

                        tagType = TagType.from(qName);
                        if (qName.equals("item")) {
                            movie = new Movie();
                            movies.add(movie);
                            id++;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (tagType.isPresent()) {
                            Characters characters = event.asCharacters();
                            String data = characters.getData().trim();
                            //System.out.println(data);
                            if (tagType.get().toString().equals("CHANNEL")) {

                            }
                            switch (tagType.get()) {
                                case TITLE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setTitle(data);
                                    }
                                    case DESCRIPTION:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setDescription(TextFormator.formatDescription(data));
                                    }
                                    break;
                                case REDATELJ:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setRedatelj(data);
                                    }
                                    break;
                                case GLUMCI:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setGlumci(data);
                                    }
                                    break;
                                case TRAJANJE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setTrajanje(TextFormator.formatTrajanje(data));
                                    }
                                    break;
                                case GODINA:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setGodina(data);
                                    }
                                    break;
                               
                            }
                        }
                        break;
                }
            }
        }  
        
        return movies;
    }


    private enum TagType {
        CHANNEL("channel"),
        ITEM("item"), 
        TITLE("title"), 
        DESCRIPTION("description"),
        ORIGINALANNAZIV("orignaziv"),
        GLUMCI("glumci"),
        REDATELJ("redatelj"),
        TRAJANJE("trajanje"),
        GODINA("godina");
        

        private final String name;

        private TagType(String name) {
            this.name = name;
        }

        private static Optional<TagType> from(String name) {
            for (TagType value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    }

}

