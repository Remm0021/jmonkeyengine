/*
 * Copyright (c) 2003-2007 jMonkeyEngine
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

package jmetest.awt.applet;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.jmex.audio.AudioSystem;
import com.jmex.audio.AudioTrack;
import com.jmex.audio.MusicTrackQueue;
import com.jmex.audio.MusicTrackQueue.RepeatType;
import com.jmex.awt.applet.SimpleJMEApplet;

public class AppletTestSounds extends SimpleJMEApplet {
    private static final Logger logger = Logger
            .getLogger(AppletTestSounds.class.getName());

    private static final long serialVersionUID = 1L;

    @Override
    public void simpleAppletSetup() {
        try {
            AudioTrack track = AudioSystem.getSystem().createAudioTrack(
                    AppletTestSounds.class.getClassLoader().getResource(
                            "jmetest/data/sound/Footsteps.wav"), false);
            MusicTrackQueue queue = AudioSystem.getSystem().getMusicQueue();
            queue.setCrossfadeinTime(0);
            queue.setRepeatType(RepeatType.ONE);
            queue.addTrack(track);
            queue.play();
        } catch (Exception e) {
            logger.logp(Level.SEVERE, this.getClass().toString(),
                    "simpleAppletSetup()", "Exception", e);
        }
    }
    
    @Override
    public void simpleAppletUpdate() {
        AudioSystem.getSystem().update();
    }
}