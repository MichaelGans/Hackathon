import java.io.*;
import java.util.*;
public class Main {
	public static void main(String args[]) throws FileNotFoundException{
		String [] messages=new String[1000];
		String [] chats=new String[100];
		Integer numberOfChats=-1;
		Integer numberOfMessages=0;
		Integer numberOfWords=0;
		TreeMap<String,Integer> words=new TreeMap<String,Integer>();
		File folder = new File("/Users/Michael/Documents/Developer/gws-emulator/server/chats");
		File chat = new File("");
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
	        chat=new File("/Users/Michael/Documents/Developer/gws-emulator/server/chats/"+file.getName());
	        //System.out.println(file.getName());
	        numberOfChats++;
	        Scanner scan = new Scanner(chat);
	        String allString = new String();
	        while(scan.hasNextLine()){
	        	allString+=scan.nextLine();
	        }
	        String [] allSegments = new String[10000];
	        allSegments=allString.split("\"");
	        for(int i=0; i<allSegments.length;i++){
	        	//System.out.println(allSegments[i]);
	        	if(allSegments[i].equals("Customer")&&allSegments[i+1].equals("},")&&allSegments[i+2].equals("text")&&allSegments[i+3].equals(":")){
	        		messages[numberOfMessages]=allSegments[i+4];
	        		if(chats[numberOfChats]==null){
	        			chats[numberOfChats]="Customer: "+messages[numberOfMessages]+"\n";
	        		}
	        		else{
	        			chats[numberOfChats]+="Customer: "+messages[numberOfMessages]+"\n";
	        		}
	        		numberOfMessages++;
	        	}
	        	if(allSegments[i].equals("Agent")&&allSegments[i+1].equals("},")&&allSegments[i+2].equals("text")&&allSegments[i+3].equals(":")){
	        		chats[numberOfChats]+="Agent: "+allSegments[i+4]+"\n";
	        	}
	        }
		}
		for(int i=0;i<numberOfMessages;i++){
			String wordIndex[] = new String[1000];
			wordIndex=messages[i].split("[\\[\\] \n\t\r.,;:!?(){}]");
			for(int j=0;j<wordIndex.length;j++){
				//System.out.println(wordIndex[j]);
				String key=wordIndex[j].toLowerCase();
				if(words.get(key)==null){
					words.put(key,1);
				}
				else{
					int value=words.get(key);
					value++;
					words.put(key,value);
				}
			}
		}
		File fileForSite = new File("/Users/Michael/Documents/Developer/fileForSite.txt");
		PrintWriter output = new PrintWriter(fileForSite);
		for(int i=1;i<=numberOfChats;i++){
			output.println("Chat number "+ i+":");
			output.println(chats[i]);
		}
		output.println("\nTrends");
		String holder = words.toString();
		String [] holderArray=holder.split("[\\[\\] \n\t\r.,;:!?(){}]");
		for(int i=0;i<holderArray.length;i++){
			output.println(holderArray[i]);
		}
		output.close();
	}
}
