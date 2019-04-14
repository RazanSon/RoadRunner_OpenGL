package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;



/**
 * Roadrunner.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Roadrunner implements GLEventListener {
 Texture tex;
    public static void main(String[] args) {
        Frame frame = new Frame("Road Runner");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Roadrunner());
        frame.add(canvas);
        frame.setSize(1200, 800);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(1.0f,1.0f, 1.0f, 1f);
        gl.glShadeModel(GL.GL_FLAT); // try setting this to GL_FLAT and see what happens.
        //gl.glEnable(GL.GL_TEXTURE_2D); //activate texture mapping for 2D
        
        try{
        //load texture
        tex = TextureIO.newTexture(new File("4.JPG"), true);
        
        }
        catch(IOException ex){
        System.err.println(ex);
        }
        
    }
    

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        // Move the "drawing cursor" around
        tex.enable();
        gl.glTranslatef(0.0f, 0.0f, -6.0f);
        tex.bind();
                gl.glColor3f(1f,1f,1f);

            gl.glBegin(GL.GL_QUADS);
            
            gl.glTexCoord2f(0,1);
            gl.glVertex2f(-4,-2.41f);
            
            gl.glTexCoord2f(1,1);
            gl.glVertex2f(4,-2.41f);
            
            gl.glTexCoord2f(1,0);
            gl.glVertex2f(4,2.41f);
            
            gl.glTexCoord2f(0,0);
            gl.glVertex2f(-4,2.41f);

            gl.glEnd ();
            tex.disable();
        gl.glTranslatef(-2f, -.5f, -3f);

        //first line
        gl.glColor3f(0,0,0);
        gl.glRectf(0,2f, 0.2f,2.2f);
        gl.glRectf(-1.4f,2f, 0f,2.2f);
        //2nd line
        gl.glRectf(-1.6f,1.8f, -1.4f,2f);        
        gl.glColor3f(0.349f,0.254f,0.686f); //purple
        gl.glRectf(-1.4f,1.8f, 0.2f,2f);
        gl.glColor3f(0,0,0);
        gl.glRectf(0f,1.8f, 0.4f,2f);
        //3rd line
        gl.glRectf(-1.8f,1.6f, -1.6f,1.8f);
        gl.glColor3f(0.349f,0.254f,0.686f); //purple
        gl.glRectf(-1.6f,1.6f, 0.4f,1.8f);
        gl.glColor3f(0,0,0);
        gl.glRectf(0.2f,1.6f, 0.4f,1.8f);
        gl.glRectf(0.6f,1.6f, 1.2f,1.8f);
        //4th line 
        gl.glRectf(-1.8f,1.4f, -1.6f,1.8f);
        gl.glColor3f(0.349f,0.254f,0.686f); //purple
        gl.glRectf(-1.6f,1.4f, -1.2f,1.6f);
        gl.glColor3f(0,0,0);
        gl.glRectf(-1.2f,1.4f, -1f,1.6f);
        gl.glColor3f(0.349f,0.254f,0.686f); //purple
        gl.glRectf(-1f,1.4f, -.6f,1.6f);
        gl.glColor3f(0,0,0);
        gl.glRectf(-.6f,1.4f, -.4f,1.6f);
        gl.glColor3f(0.349f,0.254f,0.686f); //purple
        gl.glRectf(-.4f,1.4f, .2f,1.6f);
        gl.glColor3f(0,0,0);
        gl.glRectf(.2f,1.4f, .6f,1.6f);
        gl.glColor3f(0.412f,0.788f,0.792f); //blue
        gl.glRectf(.6f,1.4f, 1f,1.6f);
        gl.glColor3f(0,0,0);
        gl.glRectf(1f,1.4f, 1.2f,1.6f);
        //5th line
        gl.glRectf(-1.6f,1.2f, -.6f,1.4f);
        gl.glColor3f(0.349f,0.254f,0.686f); //purple
        gl.glRectf(-.6f,1.2f, 0f,1.4f);
        gl.glColor3f(0,0,0);
        gl.glRectf(0f,1.2f, 0.2f,1.4f);
        gl.glColor3f(0.412f,0.788f,0.792f); //blue
        gl.glRectf(.2f,1.2f, 1f,1.4f);
        gl.glColor3f(0,0,0);
        gl.glRectf(1f,1.2f, 1.2f,1.4f);
        //6th line 
        gl.glRectf(-1f,1f, -0.8f,1.2f);
        gl.glColor3f(0.349f,0.254f,0.686f); //purple
        gl.glRectf(-.8f,1f, -.2f,1.2f);
        gl.glColor3f(0,0,0);
        gl.glRectf(-.2f,1f, 0f,1.2f);
        gl.glColor3f(0.412f,0.788f,0.792f); //blue
        gl.glRectf(0f,1f, .6f,1.2f);
        gl.glColor3f(0,0,0);
        gl.glRectf(.6f,1f, 1f,1.2f);
        //7th line
        gl.glRectf(-1.2f,.8f, -0.8f,1f);
        gl.glColor3f(0.349f,0.254f,0.686f); //purple
        gl.glRectf(-.8f,.8f, -.4f,1f);
        gl.glColor3f(0,0,0);
        gl.glRectf(-.4f,.8f, 0f,1f);
        gl.glColor3f(0.412f,0.788f,0.792f); //blue
        gl.glRectf(0f,.8f, 1f,1f);
        gl.glColor3f(0,0,0);
        gl.glRectf(1f,.8f, 1.2f,1f);
        //8th line 
        gl.glRectf(-1.4f,.6f, -1.2f,.8f);
        gl.glColor3f(1.0f,1.0f,1.0f);
        gl.glRectf(-1.2f,.6f, -.8f,.8f);
        gl.glColor3f(0.349f,0.254f,0.686f); //purple
        gl.glRectf(-.8f,.6f, -.4f,.8f);
        gl.glColor3f(0,0,0);
        gl.glRectf(-.4f,.6f, 0f,.8f);
        gl.glColor3f(0.412f,0.788f,0.792f); //blue
        gl.glRectf(0f,.6f, .6f,.8f);
        gl.glColor3f(0,0,0);
        gl.glRectf(.6f,.6f, 1f,.8f);
        //9th line 
        gl.glRectf(-1.8f,.4f, -1.2f,.6f);
        gl.glColor3f(1.0f,1.0f,1.0f); //white
        gl.glRectf(-1.2f,.4f, -.6f,.6f);
        gl.glColor3f(0.349f,0.254f,0.686f); //purple
        gl.glRectf(-.6f,.4f, -.4f,.6f);
        gl.glColor3f(0,0,0);
        gl.glRectf(-.4f,.4f, -.2f,.6f);
        gl.glColor3f(0.349f,0.254f,0.686f); //purple
        gl.glRectf(-.2f,.4f, 0f,.6f);
        gl.glColor3f(0,0,0);
        gl.glRectf(0f,.4f, .2f,.6f);
        gl.glColor3f(0.412f,0.788f,0.792f); //blue
        gl.glRectf(0.2f,.4f, .8f,.6f);
        gl.glColor3f(0,0,0);
        gl.glRectf(0.8f,.4f, 1f,.6f);
        //10th line 
        gl.glRectf(-2f,.2f, -1.8f,.4f);
        gl.glColor3f(217/255f,158/255f,67/255f); //Orange
        gl.glRectf(-1.8f,.2f, -1.2f,.4f);
        gl.glColor3f(0,0,0);
        gl.glRectf(-1.2f,.2f, -1f,.4f);
        gl.glColor3f(1.0f,1.0f,1.0f); //white
        gl.glRectf(-1f,.2f, -.6f,.4f);
        gl.glColor3f(0.349f,0.255f,0.686f); //purple
        gl.glRectf(-.6f,.2f, -.4f,.4f);
        gl.glColor3f(0,0,0);
        gl.glRectf(-.4f,.2f, -.2f,.4f);
        gl.glColor3f(0.349f,0.254f,0.686f); //purple
        gl.glRectf(-.2f,.2f, 0f,.4f);
        gl.glColor3f(0,0,0);
        gl.glRectf(0f,.2f, .2f,.4f);
        gl.glColor3f(0.412f,0.788f,0.792f); //blue
        gl.glRectf(0.2f,.2f, 1f,.4f);
        gl.glColor3f(0,0,0);
        gl.glRectf(0.4f,.2f, .6f,.4f);
        gl.glRectf(1f,.2f, 1.2f,.4f);
        //11th line 
        gl.glRectf(-2f,0f, -1.8f,.2f);
        gl.glColor3f(217/255f,158/255f,67/255f); //Orange
        gl.glRectf(-1.8f,0f, -.2f,.2f);
        gl.glColor3f(0,0,0);
        gl.glRectf(-1.2f,0f, -1f,.2f);
        gl.glColor3f(1.0f,1.0f,1.0f); //white
        gl.glRectf(-1f,0f, -.6f,.2f);
        gl.glColor3f(0,0,0);
        gl.glRectf(-.2f,0f, 1f,.2f);
        gl.glColor3f(0.412f,0.788f,0.792f); //blue
        gl.glRectf(0.2f,0f, .6f,.2f);
        //12th line 
        gl.glColor3f(0,0,0);
        gl.glRectf(-1.8f,-.2f, .2f,0f);
        gl.glColor3f(217/255f,158/255f,67/255f); //Orange
        gl.glRectf(-1.6f,-.2f, -.2f,0f);
        gl.glColor3f(0.412f,0.788f,0.792f); //blue
        gl.glRectf(0.2f,-.2f, .8f,0f);
        gl.glColor3f(0,0,0);
        gl.glRectf(0.4f,-.2f, .6f,0f);
        gl.glRectf(0.8f,-.2f, 1f,0f);
        //13th line 
        gl.glRectf(-1.6f,-.4f, 0.8f,-.2f);
        gl.glColor3f(217/255f,158/255f,67/255f); //Orange
        gl.glRectf(-1.2f,-.4f, -.4f,-.2f);
        gl.glColor3f(0.412f,0.788f,0.792f); //blue
        gl.glRectf(0f,-.4f, .2f,-.2f);
        //14th line 
        gl.glColor3f(0,0,0);
        gl.glRectf(-1.2f,-.6f, 0.4f,-.4f);
        gl.glColor3f(0.412f,0.788f,0.792f); //blue
        gl.glRectf(-1f,-.6f, -.6f,-.4f);
        gl.glRectf(0f,-.6f, .2f,-.4f);
        //15th line 
        gl.glColor3f(0,0,0);
        gl.glRectf(-1f,-.8f, .6f,-.6f);
        gl.glColor3f(0.412f,0.788f,0.792f); //blue
        gl.glRectf(-.8f,-.8f, -.6f,-.6f);
        gl.glColor3f(0.349f,0.254f,0.686f); //purple
        gl.glRectf(-.4f,-.8f, 0.4f,-.6f);
        
        gl.glColor3f(1,1,1);
        gl.glPointSize(30);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2f(1f,-1.1f);
        gl.glEnd();
        gl.glPointSize(60);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2f(1.6f,-.8f);
        gl.glEnd();
        gl.glPointSize(70);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2f(2.45f,-.6f);
        gl.glEnd();
        
        //16th line 
        gl.glColor3f(0,0,0);
        gl.glRectf(-1f,-1f, .4f,-.8f);
        gl.glColor3f(0.412f,0.788f,0.792f); //blue
        gl.glRectf(-.8f,-1f, .2f,-.8f);
        gl.glColor3f(0.349f,0.254f,0.686f); //purple
        gl.glRectf(-.6f,-1f, 0f,-.8f);
        //17th line 
        gl.glColor3f(0,0,0);
        gl.glRectf(-.8f,-1.2f, .2f,-1f);
        gl.glColor3f(0.412f,0.788f,0.792f); //blue
        gl.glRectf(-.6f,-1.2f, 0,-1f);
        //18th line 
        gl.glColor3f(0,0,0);
        gl.glRectf(-.6f,-1.4f, 0f,-1.2f);
        //19th line 
        gl.glRectf(-1.6f,-1.6f,-1f,-1.4f);
        gl.glRectf(-.6f,-1.6f,0f,-1.4f);
        gl.glRectf(.4f,-1.6f,1f,-1.4f);
        gl.glColor3f(217/255f,158/255f,67/255f); //Orange
        gl.glRectf(-.4f,-1.6f,-.2f,-1.4f);
        //20th line 
        gl.glColor3f(0,0,0);
        gl.glRectf(-1.8f,-1.8f,1.2f,-1.6f);
        gl.glColor3f(217/255f,158/255f,67/255f); //Orange
        gl.glRectf(-.4f,-1.8f,-.2f,-1.6f);
        gl.glColor3f(90/255f,58/255f,14/255f); //Brown
        gl.glRectf(-1.6f,-1.8f,-1f,-1.6f);
        gl.glRectf(.4f,-1.8f,1,-1.6f);
        //21 line 
        gl.glColor3f(0,0,0);
        gl.glRectf(-1.8f,-2f,1.2f,-1.8f);
        gl.glColor3f(90/255f,58/255f,14/255f); //Brown
        gl.glRectf(-1.6f,-2f,-.6f,-1.8f);
        gl.glRectf(0f,-2f,1,-1.8f);
        //22 Line 
        gl.glColor3f(0,0,0);
        gl.glLineWidth(2000f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(-1.8f, -2f);
        gl.glVertex2f(1.2f, -2f);
        gl.glEnd();
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(-1.8f, -2.05f);
        gl.glVertex2f(1.2f, -2.05f);
        gl.glEnd();
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(-1.8f, -2.1f);
        gl.glVertex2f(1.2f, -2.1f);
        gl.glEnd();
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    
}

