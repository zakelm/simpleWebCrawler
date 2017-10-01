package crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class crawler{
	
	public List<String> readFile( File fileName, String className) throws IOException{

	    System.out.println(fileName);
		ArrayList<String> links = new ArrayList<String>();
		
		List<String> fileContent = FileUtils.readLines(fileName);
		
		return fileContent;  
	}
	
	public void dt() {
		System.out.println("test");
	}
	
	public static void main(String[] args) throws IOException, ConnectException{		
		Document document;
		String content;
		FileWriter fw;
		
		crawler craw = new crawler();

	    File folder      = new File( "links" );
		File[] filesList = folder.listFiles();
		String[] lines   = new String[ filesList.length ];

		for ( int i = 0; i < filesList.length; i++ ) {
			File file = filesList[ i ];
			if ( file.isFile() ) {
				List <String> links = craw.readFile(file, file.getName());

				for ( int j = 0; j < links.size(); j++ ) {
				    try{
				    	System.out.println((String)links.get(j));
				    	document = Jsoup.connect((String)links.get(j)).get();	    
					    content = document.select( ".job-paragraph" ).text();
					    System.out.println(content);
					    fw = new FileWriter( "data/" + file.getName() + "/" + j + ".txt" ); 
				        fw.write( content );      						 
				        fw.close(); 
				    }catch( MalformedURLException m) {
				    	
				    	System.err.print(m.getMessage() + j);
				    } 
				}
			}
		}
	}
    
}
