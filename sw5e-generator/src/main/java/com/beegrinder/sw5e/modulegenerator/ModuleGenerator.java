package com.beegrinder.sw5e.modulegenerator;


import com.beegrinder.sw5e.objects.Equipment;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.fasterxml.jackson.databind.node.ArrayNode;


public class ModuleGenerator {




	static Properties defaultProps = new Properties();
	static AppScreen frame = null;




	public static void loadDefaultProperties() throws IOException {

		List< Equipment > equipmentList;
		FileInputStream in = new FileInputStream( AppConstants.DEFAULT_PROPERTIES_FILENAME );
		defaultProps.load( in );
		in.close();

	}




	public static void main( String[] args ) {
		// load properties
		try {
			loadDefaultProperties();
			if ( ! AppHelper.validateDefaultProperties( defaultProps ) ) {
				throw new IOException( "Invalid defaultProperties file data found." );
			}
		} catch ( Exception e ) {
			System.out.println( "Unable to load default properties. " + e.getMessage() );
			System.exit( 1 );
		}

		EventQueue.invokeLater( new Runnable() {

			public void run() {

				try {
					frame = new AppScreen();

					AppHelper.populateDefaultsToScreen( frame, defaultProps );  //sloppy reference call
					AppHelper.refreshEquipmentList( AppHelper.getEquipmentUrl( defaultProps ) );

					frame.setVisible( true );
				} catch ( Exception e ) {
					e.printStackTrace();
				}
			}
		} );
	}

}