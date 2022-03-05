/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Marker;
import com.sothawo.mapjfx.event.MapViewEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class mapModifierMagasinController implements Initializable {

    @FXML
    private Button btnConf;
    @FXML
    private MapView map;

    /**
     * Initializes the controller class.
     */
     private ModifierMgController ej;
    private Marker markerPlace;
    private double lat = 0;
    private double lng = 0;
    private String lieu = "";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   map.initialize();

        Coordinate center1 = new Coordinate(35.6262, 10.3989);
        map.setCenter(center1);
        markerPlace = Marker.createProvided(Marker.Provided.BLUE).setPosition(null).setVisible(
                true);

        map.setZoom(7);
        map.addEventHandler(MapViewEvent.MAP_CLICKED, event -> {
            event.consume();
            final Coordinate newPosition = event.getCoordinate();
            if (markerPlace.getVisible()) {
                final Coordinate oldPosition = markerPlace.getPosition();
                if (oldPosition != null) {
                    animateClickMarker(oldPosition, newPosition);
                } else {
                    markerPlace.setPosition(newPosition);
                    // adding can only be done after coordinate is set
                    map.addMarker(markerPlace);
                }
            }

            this.lat = newPosition.getLatitude();
            this.lng = newPosition.getLongitude();

            /*  Coordinate center2 = new Coordinate(35.6262, 10.3989);

            Marker marke = Marker.createProvided(Marker.Provided.BLUE).setPosition(newPosition).setVisible(
                    true);
            System.out.println(newPosition);
            map.addMarker(marke);*/
        });
    }

    public void init(ModifierMgController ajc) {
        this.ej = ajc;

    }

    private void animateClickMarker(Coordinate oldPosition, Coordinate newPosition) {
        // animate the marker to the new position
        final Transition transition = new Transition() {
            private final Double oldPositionLongitude = oldPosition.getLongitude();
            private final Double oldPositionLatitude = oldPosition.getLatitude();
            private final double deltaLatitude = newPosition.getLatitude() - oldPositionLatitude;
            private final double deltaLongitude = newPosition.getLongitude() - oldPositionLongitude;

            {
                setCycleDuration(Duration.seconds(1.0));
                setOnFinished(evt -> markerPlace.setPosition(newPosition));
            }

            @Override
            protected void interpolate(double v) {
                final double latitude = oldPosition.getLatitude() + v * deltaLatitude;
                final double longitude = oldPosition.getLongitude() + v * deltaLongitude;
                markerPlace.setPosition(new Coordinate(latitude, longitude));
            }
        };
        transition.play();
    }

    @FXML
    private void confirmerPlace(MouseEvent event) {

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://nominatim.openstreetmap.org/reverse?format=geocodejson&lat=" + this.lat + "&lon=" + this.lng);

// Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<>(2);
        /*params.add(new BasicNameValuePair("param-1", "12345"));
params.add(new BasicNameValuePair("param-2", "Hello!"));*/
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException ex) {

        }

//Execute and get the response.
        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response != null) {
                String responseString = new BasicResponseHandler().handleResponse(response);
                JSONParser parser = new JSONParser();

                try {
                    Object obj = parser.parse(responseString);
//                  JSONArray array = (JSONArray)obj;
                    JSONObject obj2 = (JSONObject) obj;
                    JSONArray array = (JSONArray) obj2.get("features");
                    JSONObject obj3 = (JSONObject) array.get(0);
                    JSONObject obj4 = (JSONObject) obj3.get("properties");
                    JSONObject obj5 = (JSONObject) obj4.get("geocoding");
                    this.lieu = obj5.get("label").toString();

                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

            }
            HttpEntity entity = response.getEntity();

            if (entity != null) {

                try (InputStream instream = entity.getContent()) {

                    // do something useful
                } catch (IOException ex) {
                    System.out.println("erreur1");
                }

            }
        } catch (IOException ex) {
            System.out.println("erreur2");
        }

        this.ej.setCoordnate(this.lat, this.lng, this.lieu);
        Window window = ((Node) (event.getSource())).getScene().getWindow();
        if (window instanceof Stage) {
            ((Stage) window).close();
        }
    }

}
