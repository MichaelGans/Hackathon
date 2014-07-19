import java.io.*;
import java.util.*;
public class Main {
	public static void main(String args[]) throws FileNotFoundException{
		String [] messages=new String[1000];
		String [] chats=new String[100];
		Integer numberOfChats=-1;
		Integer numberOfMessages=0;
		Integer numberOfWords=0;
		HashMap<String,Integer> words=new HashMap<String,Integer>();
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
			System.out.println(messages[i]);
			String key=messages[i].replace(" ","");
			key="#"+key;
			if(words.get(key)==null){
				words.put(key,1);
			}
			else{
				int value=words.get(key);
				value++;
				words.put(key,value);
			}
		}
		//System.out.println(words);
		File fileForSite = new File("/Users/Michael/Desktop/Hackathon/fileForSite.txt");
		PrintWriter output = new PrintWriter(fileForSite);
		Object [] valueHolder = words.values().toArray();
		Integer [] integerHolder= new Integer[valueHolder.length];
		for(int i=0;i<valueHolder.length;i++){
			integerHolder[i]=(Integer) valueHolder[i];
		}
		Object [] namesHolder=words.keySet().toArray();
		//System.out.println(valueHolder[1]);
		//System.out.println(namesHolder[1]);
		String Print=null;
		for(int j=0;j<words.size();j++){
			for(int i=0;i<words.size()-1;i++){
				if(integerHolder[i]<integerHolder[i+1]){
					int swap=integerHolder[i+1];
					Object swap1=namesHolder[i+1];
					integerHolder[i+1]=integerHolder[i];
					namesHolder[i+1]=namesHolder[i];
					namesHolder[i]=swap1;
					integerHolder[i]=swap;
				}
			}
		}
		for(int i=0;i<namesHolder.length;i++){
			if(Print==null){
				Print=namesHolder[i].toString();
			}
			else{
			Print+=" "+namesHolder[i].toString();
			}
		}
		output.println(Print);
//		output.println("\n\n\n");
//		for(int i=1;i<=numberOfChats;i++){
//			output.println("Chat number "+ i+":");
//			output.println(chats[i]);
//		}
		output.close();
	}
}
