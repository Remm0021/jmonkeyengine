/*
 * Copyright (c) 2009-2010 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.jme3.app.state;

import com.jme3.app.Application;
import com.jme3.renderer.RenderManager;
import java.util.ArrayList;

public class AppStateManager {

    private final ArrayList<AppState> states = new ArrayList<AppState>();
    private final Application app;

    public AppStateManager(Application app){
        this.app = app;
    }

    /**
     * Attach a state to the AppStateManager, the same state cannot be attached
     * twice.
     *
     * @param state The state to attach
     * @return True if the state was successfully attached, false if the state
     * was already attached.
     */
    public boolean attach(AppState state){
        synchronized (states){
            if (!states.contains(state)){
                state.stateAttached(this);
                states.add(state);
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * Detaches the state from the AppStateManager. 
     *
     * @param state The state to detach
     * @return True if the state was detached successfully, false
     * if the state was not attached in the first place.
     */
    public boolean detach(AppState state){
        synchronized (states){
            if (states.contains(state)){
                state.stateDetached(this);
                states.remove(state);
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * Check if a state is attached or not.
     *
     * @param state The state to check
     * @return True if the state is currently attached to this AppStateManager.
     * 
     * @see AppStateManager#attach(com.jme3.app.state.AppState)
     */
    public boolean hasState(AppState state){
        synchronized (states){
            return states.contains(state);
        }
    }

    /**
     * Returns the first state that is an instance of subclass of the specified class.
     * @param <T>
     * @param stateClass
     * @return First attached state that is an instance of stateClass
     */
    public <T extends AppState> T getState(Class<T> stateClass){
        synchronized (states){
            int num = states.size();
            for (int i = 0; i < num; i++){
                AppState state = states.get(i);
                if (stateClass.isAssignableFrom(state.getClass())){
                    return (T) state;
                }
            }
        }
        return null;
    }

    /**
     * Calls update for attached states, do not call directly.
     * @param tpf Time per frame.
     */
    public void update(float tpf){
        synchronized (states){
            int num = states.size();
            for (int i = 0; i < num; i++){
                AppState state = states.get(i);
                if (!state.isInitialized())
                    state.initialize(this, app);

                state.update(tpf);
            }
        } 
    }

    /**
     * Calls render for all attached states, do not call directly.
     * @param rm The RenderManager
     */
    public void render(RenderManager rm){
        synchronized (states){
            int num = states.size();
            for (int i = 0; i < num; i++){
                AppState state = states.get(i);
                if (!state.isInitialized())
                    state.initialize(this, app);

                state.render(rm);
            }
        }
    }

    /**
     * Calls render for all attached states, do not call directly.
     * @param rm The RenderManager
     */
    public void postRender(){
        synchronized (states){
            int num = states.size();
            for (int i = 0; i < num; i++){
                AppState state = states.get(i);
                if (!state.isInitialized())
                    state.initialize(this, app);

                state.postRender();
            }
        }
    }

    /**
     * Calls cleanup on attached states, do not call directly.
     */
    public void cleanup(){
        synchronized (states){
            for (int i = 0; i < states.size(); i++){
                AppState state = states.get(i);
                state.cleanup();
            }
        }
    }
}
