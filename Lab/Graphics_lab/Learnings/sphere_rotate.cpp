
#include <GL/freeglut_std.h>
#include <GL/gl.h>
#include <GL/glu.h>
#include <GL/glut.h>

using namespace std;

float x=0;

void timer(int time){
    x = x + 5;
    if(x > 360)
        x = 0;

    glutPostRedisplay();
    glutTimerFunc(16,timer,0);
}

void myInit(){
    glClearColor(1.0,1.0,1.0,0.0);
    glClearDepth(1.0f);
    glEnable(GL_DEPTH_TEST);
    glEnable(GL_LIGHTING);
    glEnable(GL_LIGHT0);
    glDepthFunc(GL_LEQUAL);

    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    //gluPerspective(45,1,10,500);
    glOrtho(-320,320,-320,320,-500,500);
}

void run(){
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

    glTranslatef(0,0,-350);
    
    GLfloat pos[] = {0,200,100,1};
    GLfloat diffLight[] = {0,0,1,1};
    GLfloat ambLight[] = {0,0.5,0,1};
    //glLightfv(GL_LIGHT0,GL_POSITION,pos);
    //glLightfv(GL_LIGHT0,GL_DIFFUSE,diffLight);
    //glLightfv(GL_LIGHT0,GL_AMBIENT,ambLight);

    GLfloat diff[] = {1,0,0,1};
    GLfloat amb[] = {0,0,0,1};

    glColor3f(0,0,0);
    // glMaterialfv(GL_FRONT_AND_BACK,GL_DIFFUSE,diff);
    // glMaterialfv(GL_FRONT_AND_BACK,GL_AMBIENT,amb);

    glPushMatrix();
    glRotatef(x,1,0,0);
    glutSolidSphere(100,20,20);
    glPopMatrix();

    glutSwapBuffers();
    glFlush();
}


int main(int argc, char **argv){
    glutInit(&argc,argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
    glutInitWindowSize(640,640);
    glutCreateWindow("Practice");
    glutDisplayFunc(run);
    glutTimerFunc(0,timer,0);
    myInit();
    glutMainLoop();
    return 0;
}
