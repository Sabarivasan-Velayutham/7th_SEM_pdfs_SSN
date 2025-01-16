
#include <GL/glut.h>
#include <math.h>
#include <stdio.h>
#include <iostream>

using namespace std;

GLfloat R,px,py;

void Draw()
{
    glClear(GL_COLOR_BUFFER_BIT);

    glColor3f(0,0,1);
    glBegin(GL_LINE_LOOP);
        glVertex2f(0.1,0.4);
        glVertex2f(0.7,0.4);
        glVertex2f(0.4,0.8);
    glEnd();

    glLoadIdentity();

    glTranslatef(px,py,0);
    glRotatef(R,0,0,1);
    glTranslatef(-px,-py,0);

    glColor3f(1,0,0);
    glBegin(GL_LINE_LOOP);
        glVertex2f(0.1,0.4);
        glVertex2f(0.7,0.4);
        glVertex2f(0.4,0.8);
    glEnd();

    glFlush();
}

int main(int argC,char *argV[])
{
    cout << "\nEnter the Rotation Reference Point [Pivot Point] : ";
    cin >> px >> py;
    cout << "\n\nEnter the Rotation Degree : ";
    cin >> R;
    glutInit(&argC,argV);
    glutInitDisplayMode(GLUT_RGB|GLUT_SINGLE);
    glutInitWindowPosition(0,0);
    glutInitWindowSize(500,500);
    glutCreateWindow("Triangle Rotation");
    glutDisplayFunc(Draw);
    glutMainLoop();
    return 0;
}