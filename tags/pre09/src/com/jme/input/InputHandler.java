/*
 * Copyright (c) 2003, jMonkeyEngine - Mojo Monkey Coding
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this 
 * list of conditions and the following disclaimer. 
 * 
 * Redistributions in binary form must reproduce the above copyright notice, 
 * this list of conditions and the following disclaimer in the documentation 
 * and/or other materials provided with the distribution. 
 * 
 * Neither the name of the Mojo Monkey Coding, jME, jMonkey Engine, nor the 
 * names of its contributors may be used to endorse or promote products derived 
 * from this software without specific prior written permission. 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */
package com.jme.input;

import java.util.ArrayList;

import com.jme.input.action.InputAction;
import com.jme.input.action.MouseInputAction;

/**
 * <code>InputHandler</code>
 * @author Mark Powell
 * @version $Id: InputHandler.java,v 1.1 2004-03-25 17:14:38 mojomonkey Exp $
 */
public class InputHandler {
    private ArrayList actions;
    private ArrayList mouseActions;
    private KeyBindingManager keyboard;
    private Mouse mouse;

    public InputHandler() {
        actions = new ArrayList();
        mouseActions = new ArrayList();
    }

    public void setKeyBindingManager(KeyBindingManager keyboard) {
        this.keyboard = keyboard;
    }
    
    public KeyBindingManager getKeyBindingManager() {
        return keyboard;
    }
    
    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }
    
    public Mouse getMouse() {
        return mouse;
    }
    
    public void setKeySpeed(float speed) {
        for(int i = 0; i < actions.size(); i++) {
            ((InputAction)actions.get(i)).setSpeed(speed);
        }
    }
    
    public void setMouseSpeed(float speed) {
        for(int i = 0; i < mouseActions.size(); i++) {
            ((MouseInputAction)mouseActions.get(i)).setSpeed(speed);
        }
    }

    public void addAction(InputAction inputAction) {
        actions.add(inputAction);
    }
    
    public void addAction(MouseInputAction mouseAction) {
        mouseActions.add(mouseAction);
    }

    public void removeAction(InputAction inputAction) {
        actions.remove(inputAction);
    }
    
    public void removeAction(MouseInputAction mouseAction) {
        mouseActions.remove(mouseAction);
    }

    public void update(float time) {
        if (keyboard != null) {
            keyboard.update();
            for (int i = 0; i < actions.size(); i++) {
                if (keyboard
                    .isValidCommand(((InputAction) actions.get(i)).getKey())) {
                    ((InputAction) actions.get(i)).performAction(time);
                }
            }
        }

        if(mouse != null) {
            mouse.update();
            for(int i = 0; i < mouseActions.size(); i++) {
                ((MouseInputAction)mouseActions.get(i)).performAction(time);
            }
        }
    }
}