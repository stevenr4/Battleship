/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship;

/**
 *
 * @author spex
 */
public class AI
{
	public boolean isPrevMiss(){
		//gets whether the square has already been hit/missed
            return true;
	}

	public void dumbass(){
//		xAI=rand.nextInt(10)+1;
//		yAI=rand.nextInt(10)+1;
	}

	public void common(){
//		if (hit==true && destroy == false){
//			//get x/y pos of hit
//			while (isPrevMiss()){
//				int i = rand.nextInt(8);
//				switch(i){
//					case 0:	yAI-=1;
//						break;
//					case 1: xAI+=1;
//						yAI-=1;
//						break;
//					case 2: xAI+=1;
//						break;
//					case 3: xAI+=1;
//						yAI+=1;
//						break;
//					case 4: yAI+=1;
//						break;
//					case 5: xAI-=1;
//						yAI+=1;
//						break;
//					case 6: xAI-=1;
//						break;
//					case 7: xAI-=1;
//						yAI-=1;
//						break;
//				}
//			}
//		} else{
//			while (isPrevMiss()){
//				dumbass();
//			}
//		}
	}

	public void cheater(){
//		int[] xShipSlots = {(get array of ship-squares)}
//		int[] yShipSlots = {(get array of ship-squares)}
//		int shipNum; //= number of ship-squares
//		xAI=xShipSlots[rand.nextInt(shipNum)];
//		yAi=yShipSlots[rand.nextInt(shipNum)];
//
//		while (isPrevMiss()){
//			int i = rand.nextInt(9);
//			switch(i){
//				case 0:	yAI-=1;
//					break;
//				case 1: xAI+=1;
//					yAI-=1;
//					break;
//				case 2: xAI+=1;
//					break;
//				case 3: xAI+=1;
//					yAI+=1;
//					break;
//				case 4: yAI+=1;
//					break;
//				case 5: xAI-=1;
//					yAI+=1;
//					break;
//				case 6: xAI-=1;
//					break;
//				case 7: xAI-=1;
//					yAI-=1;
//					break;
//				//case 8 is already on target
//			}
//		}
	}

	public void notes(){
		/* Not a called function, everything in here is simply notes put together. */

		/* Two ways of doing AI:
		 * 	A) Randomly selecting square and seeing if already hit/missed
		 *	B) Each turn updating an array list of squares that are still unhit,
		 *		then randomly getting a square from that array.
		 * For simplicity, and not quite knowing the base code, I have structured for
		 * 	the former. The code in this state is still quite flexable, and with
		 *	a few minor changes would be able to accomidate the latter.
		 */
	}
}
