package solitaire;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

/**
 * This class implements a simplified version of Bruce Schneier's Solitaire Encryption algorithm.
 * 
 * @author Fareen
 */
public class Solitaire {
	
	/**
	 * Circular linked list that is the deck of cards for encryption
	 */
	CardNode deckRear;
	
	/**
	 * Makes a shuffled deck of cards for encryption. The deck is stored in a circular
	 * linked list, whose last node is pointed to by the field deckRear
	 */
	public void makeDeck() {
		// start with an array of 1..28 for easy shuffling
		int[] cardValues = new int[28];
		// assign values from 1 to 28
		for (int i=0; i < cardValues.length; i++) {
			cardValues[i] = i+1;
		}
		
		// shuffle the cards
		Random randgen = new Random();
 	        for (int i = 0; i < cardValues.length; i++) {
	            int other = randgen.nextInt(28);
	            int temp = cardValues[i];
	            cardValues[i] = cardValues[other];
	            cardValues[other] = temp;
	        }
	     
	    // create a circular linked list from this deck and make deckRear point to its last node
	    CardNode cn = new CardNode();
	    cn.cardValue = cardValues[0];
	    cn.next = cn;
	    deckRear = cn;
	    for (int i=1; i < cardValues.length; i++) {
	    	cn = new CardNode();
	    	cn.cardValue = cardValues[i];
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
	    }
	}
	
	/**
	 * Makes a circular linked list deck out of values read from scanner.
	 */
	public void makeDeck(Scanner scanner) 
	throws IOException {
		CardNode cn = null;
		if (scanner.hasNextInt()) {
			cn = new CardNode();
		    cn.cardValue = scanner.nextInt();
		    cn.next = cn;
		    deckRear = cn;
		}
		while (scanner.hasNextInt()) {
			cn = new CardNode();
	    	cn.cardValue = scanner.nextInt();
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
		}
	}
	
	/**
	 * Implements Step 1 - Joker A - on the deck.=
	 */
	void jokerA() { //DONE
		CardNode temp;
		CardNode ptr=deckRear;
		if(ptr==null){
			return;
		}
		temp=deckRear.next;
		CardNode temp2=temp.next;
		if(ptr.cardValue==27){
			temp.next=ptr;
			ptr.next=temp2;
			while(temp2.next!=deckRear){
				temp2=temp2.next;
			}
			temp2.next=temp;
			temp2=temp2.next;
			deckRear=temp2;
			//System.out.println(deckRear.cardValue);
			//System.out.println(temp2.cardValue);
		}
		//System.out.println(deckRear.cardValue);
		else{
		for(temp=deckRear.next; temp!=deckRear; temp=temp.next){
			if(temp.cardValue==27){
				ptr.next=temp.next;
				temp.next=temp.next.next; 
				ptr=ptr.next;
				ptr.next=temp;
			}
			ptr=temp;
		}
		CardNode rear=deckRear;		// if joker is second to last card do this to the if statement
		if(rear.next.cardValue==27){
			deckRear=rear.next;
		}
	  }

	}
	
	/**
	 * Implements Step 2 - Joker B - on the deck.
	 */
	void jokerB() {
	    // COMPLETE THIS METHOD
		CardNode temp=deckRear.next;
		CardNode ptr1=deckRear;
		CardNode ptr2=deckRear.next;
		CardNode ptr3=deckRear.next;
		CardNode ptr4=deckRear.next;
		if(ptr1==null){
			return;
		}
		while(temp.next.next!=deckRear){
			temp=temp.next;
		}
		if(temp.next.cardValue==28){
			ptr3=temp.next;
			ptr3.next=ptr2.next;
			temp.next=ptr1;
			ptr2.next=ptr3;
			deckRear=ptr2;
		}
			
		else if(deckRear.cardValue==28){  //ptr1.cardValue==28 and ptr2=deckRear.next
			temp=deckRear.next;
			ptr3=ptr2.next.next; //gets 2 nodes after deckRear.next
			//System.out.println(ptr2.cardValue);
			//System.out.println(ptr3.cardValue);
			while(temp.next!=deckRear){
				temp=temp.next;
			}
			//System.out.println(temp.cardValue); //should be value of node right before deckRear
			temp.next=ptr2;
			ptr1.next=ptr3;
			ptr2=ptr2.next;
			ptr2.next=ptr1;
			deckRear=temp.next;
			//System.out.println(temp.cardValue);

		}
		else if(deckRear.next.cardValue==28){
			temp=deckRear.next;
			ptr3=ptr2.next.next;
			ptr1.next=ptr2.next;
			ptr2.next=ptr3.next;
			ptr3.next=ptr2;
			
		}
		else{
			temp=deckRear.next;
		for(temp=deckRear.next; temp.next!=deckRear;temp=temp.next){
			if(temp.next.cardValue==28){
				ptr1=temp;
				//System.out.println(ptr1.cardValue);
				ptr2=temp.next;
				//System.out.println(ptr2.cardValue);
				ptr3=temp.next.next.next;
				//System.out.println(ptr3.cardValue);
				ptr4=ptr3.next;
				ptr3.next=ptr2;
				ptr1.next=ptr2.next;
				ptr2.next=ptr4;

				//System.out.println(deckRear.cardValue);
				if(deckRear.next.cardValue==28){
					deckRear=deckRear.next;
				}
				break;
			}		
		}
	  }
	}
	
	/**
	 * Implements Step 3 - Triple Cut - on the deck.
	 */
	void tripleCut() {
		// COMPLETE THIS METHOD
		CardNode ptr1=deckRear.next;
		CardNode joker2=deckRear.next;
		CardNode tail=deckRear;
		CardNode temp;
		CardNode joker1=deckRear.next;
		CardNode head=deckRear.next;
		CardNode afterJB=deckRear;//after joker B
		if(deckRear==null){
			return;
		}
		//System.out.println(deckRear.cardValue);
		if(head.cardValue==27||head.cardValue==28){ //if front of list is a joker
			for(joker1=joker1.next; joker1.cardValue!=27 && joker1.cardValue!=28; joker1=joker1.next){
			}
			deckRear=joker1;
			return;
		}
		else if(tail.cardValue==27||tail.cardValue==28){ //if joker at end of list
			for(afterJB=afterJB.next; afterJB.next.cardValue!=27 && afterJB.next.cardValue!=28; afterJB=afterJB.next){
			}
			deckRear=afterJB;
			return;
		}
	  else{
		for(temp=head; temp.next!=deckRear; temp=temp.next){
			if(temp.next.cardValue==27){
				ptr1=temp; //node before joker A 
				joker1=temp.next; //joker1 points to joker A pronabbly dont even need this here
			}
			else if(temp.next.cardValue==28){
				joker2=temp.next; //joker B gets joker2
				if(temp.next.next!=head){ //if the next node is not deckrear.next
					afterJB=temp.next.next; //first node after joker B
				}
			}
	     }
	  }

	  //System.out.println(joker1.cardValue + " "+ joker2.cardValue);
	  for(temp=joker1; temp!=deckRear; temp=temp.next){
	  if(temp.cardValue==28){
		joker2.next=head;    //jokerB points to head
		ptr1.next=afterJB;	 //node before joker A would point to the first node after joker B
		tail.next=joker1; //the last node would point to joker A
		deckRear=ptr1;
		return;
	  }
	}
	for(temp=head; temp.next!=deckRear; temp=temp.next){
			if(temp.next.cardValue==28){
				ptr1=temp; //node before joker A 
				joker1=temp.next; //joker1 points to joker A pronabbly dont even need this here
			}
			else if(temp.next.cardValue==27){
				joker2=temp.next; //joker B gets joker2
				if(temp.next.next!=head){ //if the next node is not deckrear.next
					afterJB=temp.next.next; //first node after joker B
				}
			}
	     }
	    joker2.next=head;    //jokerB points to head
		ptr1.next=afterJB;	 //node before joker A would point to the first node after joker B
		tail.next=joker1; //the last node would point to joker A
		deckRear=ptr1;
		return;
	}

	/**
	 * Implements Step 4 - Count Cut - on the deck.
	 */
	void countCut() {		
		// COMPLETE THIS METHOD
		CardNode tail=deckRear;
		CardNode head=deckRear.next; //CHECKTO SEE IF IT WORKS
		CardNode temp;
		CardNode ptr2=deckRear.next;
		if(deckRear==null){
			return;
		}
		int value=tail.cardValue;
		if(value==28||value==27){
			value=27;
			return;
		}

		for(temp=deckRear.next; value!=0; temp=temp.next){
			if(value-1==0){
				ptr2=temp;
			}
			value--;
		}
		temp=deckRear.next;
		while(temp.next!=deckRear){
			temp=temp.next;
		}
		temp.next=head;
		tail.next=ptr2.next;
		ptr2.next=tail;
	}
	
	/**
	 * Gets a key. Calls the four steps - Joker A, Joker B, Triple Cut, Count Cut, then
	 * counts down based on the value of the first card and extracts the next card value 
	 * as key. But if that value is 27 or 28, repeats the whole process (Joker A through Count Cut)
	 * on the latest (current) deck, until a value less than or equal to 26 is found, which is then returned.
	 * 
	 * @return Key between 1 and 26
	 */
	int getKey() {
		int key;
		//printList(deckRear);
		jokerA();
		//printList(deckRear);
		jokerB();
		//printList(deckRear);
		tripleCut();
		//printList(deckRear);
		countCut();
		//printList(deckRear);
		//System.out.println();
		int value=deckRear.next.cardValue;
		if(value==28){
			value=27;
		}
		CardNode temp=deckRear;
		for(temp=deckRear.next; value!=1; temp=temp.next){
			value--;
		}
		temp=temp.next;
		if(temp.cardValue==27||temp.cardValue==28){
	      /*while(temp.cardValue==27||temp.cardValue==28){
			jokerA();
			jokerB();
			tripleCut();
			countCut();
			value=deckRear.next.cardValue;
			if(value==28){
				value=27;
			}
			temp=deckRear;
			for(temp=deckRear.next; value>1; temp=temp.next){
				value--;
			}
			temp=temp.next;
		  } 
		  */
		  
		  key=getKey();
		
		  //System.out.println("Temporary Key: "+key);
		  return key;
	    }
		
		key=temp.cardValue;
		//System.out.println("Final Key for getkey: "+key);
		return key;
		
		
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
	    //return -1;
	}
	
	/**
	 * Utility method that prints a circular linked list, given its rear pointer
	 * 
	 * @param rear Rear pointer
	 */
	private static void printList(CardNode rear) {
		if (rear == null) { 
			return;
		}
		System.out.print(rear.next.cardValue);
		CardNode ptr = rear.next;
		do {
			ptr = ptr.next;
			System.out.print("," + ptr.cardValue);
		} while (ptr != rear);
		System.out.println("\n");
	}

	/**
	 * Encrypts a message, ignores all characters except upper case letters
	 * 
	 * @param message Message to be encrypted
	 * @return Encrypted message, a sequence of upper case letters only
	 */
	public String encrypt(String message) {	
		// COMPLETE THIS METHOD
		message=message.toUpperCase();
		//System.out.println(message);

		String newMessage="";
		for(int i=0; i<message.length();i++){
			if(Character.isLetter(message.charAt(i))==true){
				newMessage+=message.substring(i,i+1);
				newMessage=newMessage.toUpperCase();
			}
		}

		//System.out.println(newMessage);
		String messageEncrypt="";
		for(int i=0;i<newMessage.length();i++){

			char letter=newMessage.charAt(i);
			//System.out.println('A'-'Z'); 
			//System.out.println('z'-'a');
			int key= ('A'-letter) *-1 +1;
			//System.out.println("key: "+ key);

			int keyStream=getKey();
			
			if(keyStream==27||keyStream==28){
				keyStream=getKey();
				while(keyStream==27||keyStream==28){
					keyStream=getKey();
				}
			}
			//System.out.println("KeyStream: " + keyStream);

			int keyEncrypt=key+keyStream;
			//System.out.println("Sum of key and keyStream: " + keyEncrypt);

			if(keyEncrypt>26){
				keyEncrypt=keyEncrypt-26;
			}
			/*
			messageEncrypt+= (char) (keyEncrypt-'A')*-1;
			messageEncrypt+= (char) (keyEncrypt+'A'+1);
			*/
			messageEncrypt=messageEncrypt + (char) (keyEncrypt+'A'-1);
			//System.out.println(messageEncrypt);
		}
		return messageEncrypt;

	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
	    //return null;
	}
	
	/**
	 * Decrypts a message, which consists of upper case letters only
	 * 
	 * @param message Message to be decrypted
	 * @return Decrypted message, a sequence of upper case letters only
	 */
	public String decrypt(String message) {	

		// COMPLETE THIS METHOD
	    String messageDecrypt="";
	    for (int i=0;i<message.length();i++) {
	    	char letter=message.charAt(i);
	    	int key= ('A'-letter) *-1 +1;
			//System.out.println(key);
			int keyStream=getKey();
			int keyDecrypt=0;
			if(key<=keyStream){
				keyDecrypt=(key+26)-keyStream;
			}
			else{
				keyDecrypt=key-keyStream;
	    	}
			messageDecrypt+= (char) (keyDecrypt+'A'-1);
	    }
	 	return messageDecrypt;


	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
	    //return null;
	}
}
