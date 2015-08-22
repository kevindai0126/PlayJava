package com.yundai.playjava.playjson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.util.Map;

/**
 * Created by yundai on 2015/7/7.
 */
class User {
    public enum Gender { MALE, FEMALE };

    public static class Name {
        private String _first, _last;

        public String getFirst() { return _first; }
        public String getLast() { return _last; }

        public void setFirst(String s) { _first = s; }
        public void setLast(String s) { _last = s; }

        @Override
        public String toString() {
            return "first: " + _first + " last: " + _last;
        }
    }

    private Gender _gender;
    private Name _name;
    private boolean _isVerified;
    private byte[] _userImage;

    public Name getName() { return _name; }
    public boolean isVerified() { return _isVerified; }
    public Gender getGender() { return _gender; }
    public byte[] getUserImage() { return _userImage; }

    public void setName(Name n) { _name = n; }
    public void setVerified(boolean b) { _isVerified = b; }
    public void setGender(Gender g) { _gender = g; }
    public void setUserImage(byte[] b) { _userImage = b; }

    @Override
    public String toString() {
        return _name.toString() + " gender: " + _gender + " verified: " + _isVerified + " userImage: " + new String(_userImage);
    }
}

public class PlayJackson {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        try {
            //Full Data Binding (POJO)
            User user = mapper.readValue(new File("data/user.json"), User.class);
            //mapper.writeValue(new File("data/user-modified.json"), user);
            System.out.println(user);

            //"Raw" Data Binding
            Map userData = mapper.readValue(new File("data/user.json"), Map.class);
            System.out.println(userData);

            //Tree Model
            // can either use mapper.readTree(source), or mapper.readValue(source, JsonNode.class);
            JsonNode rootNode = mapper.readTree(new File("data/user.json"));
            // ensure that "last name" isn't "Xmler"; if is, change to "Jsoner"
            JsonNode nameNode = rootNode.path("name");
            String lastName = nameNode.path("last").textValue();
            if ("Sixpack".equalsIgnoreCase(lastName)) {
                ((ObjectNode)nameNode).put("last", "Jsoner");
            }
            System.out.println(rootNode);

            //Streaming API
            JsonFactory f = new JsonFactory();
            JsonParser jp = f.createJsonParser(new File("data/user.json"));
            user = new User();
            jp.nextToken(); // will return JsonToken.START_OBJECT (verify?)
            while (jp.nextToken() != JsonToken.END_OBJECT) {
                String fieldname = jp.getCurrentName();
                jp.nextToken(); // move to value, or START_OBJECT/START_ARRAY
                if ("name".equals(fieldname)) { // contains an object
                    User.Name name = new User.Name();
                    while (jp.nextToken() != JsonToken.END_OBJECT) {
                        String namefield = jp.getCurrentName();
                        jp.nextToken(); // move to value
                        if ("first".equals(namefield)) {
                            name.setFirst(jp.getText());
                        } else if ("last".equals(namefield)) {
                            name.setLast(jp.getText());
                        } else {
                            throw new IllegalStateException("Unrecognized field '"+fieldname+"'!");
                        }
                    }
                    user.setName(name);
                } else if ("gender".equals(fieldname)) {
                    user.setGender(User.Gender.valueOf(jp.getText()));
                } else if ("verified".equals(fieldname)) {
                    user.setVerified(jp.getCurrentToken() == JsonToken.VALUE_TRUE);
                } else if ("userImage".equals(fieldname)) {
                    user.setUserImage(jp.getBinaryValue());
                } else {
                    throw new IllegalStateException("Unrecognized field '"+fieldname+"'!");
                }
            }
            System.out.println(user);
            jp.close(); // ensure resources get cleaned up timely and properly
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
