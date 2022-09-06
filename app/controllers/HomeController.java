package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.PumpState;
import play.mvc.Controller;
import play.mvc.Result;
import service.Database;
import service.HelperFunctions;

/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */
public class HomeController extends Controller {

	public Result index() {
		return ok(views.html.index.render());
	}

	public Result pumpState() throws Exception {

		String ausgabe = "Unbekannter Fehler";
		// ConcurrentHashmap instead of ArrayList
		ArrayList<PumpState> pumpList = Database.selectAllPumpStates();
		String jsonArray;

		ausgabe = "ArrayList erstellt: " + "\n" + pumpList + "\n";

		// Convert pumpList to JSON
		jsonArray = HelperFunctions.arrayListToJsonArray(pumpList);

		ausgabe += "ArrayList zu JsonArray konvertiert: " + "\n" + jsonArray;

		// ConcurrentHashmap: key = db_id , value = object

		return ok(ausgabe);

	}

}
