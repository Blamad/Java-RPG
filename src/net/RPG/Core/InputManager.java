package net.RPG.Core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class InputManager implements KeyListener {

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {
		case KeyEvent.VK_W:
			Core.bW = true;
			break;

		case KeyEvent.VK_S:
			Core.bS = true;
			break;

		case KeyEvent.VK_A:
			Core.bA = true;
			break;

		case KeyEvent.VK_D:
			Core.bD = true;
			break;
			
		case KeyEvent.VK_I:
			Core.bI = true;
			break;
			
		case KeyEvent.VK_SPACE:
			Core.bSPACE = true;
			break;
			
		case KeyEvent.VK_ESCAPE:
			Core.bESC = true;
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {

		case KeyEvent.VK_W:
				Core.bW = false;
			break;

		case KeyEvent.VK_S:
				Core.bS = false;
			break;

		case KeyEvent.VK_A:
				Core.bA = false;
			break;

		case KeyEvent.VK_D:
				Core.bD = false;
			break;
		case KeyEvent.VK_I:
			Core.bI = false;
		break;
		case KeyEvent.VK_SPACE:
				Core.bSPACE = false;
			break;
		case KeyEvent.VK_ESCAPE:
				Core.bESC = false;
			break;
		}
	}

	public void keyTyped(KeyEvent e) {

	}

}

