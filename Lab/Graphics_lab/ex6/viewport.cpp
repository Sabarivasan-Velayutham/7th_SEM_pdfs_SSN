

#include <GL/glut.h>
#include <stdio.h>
#include <iostream>
#include <math.h>
using namespace std;
float xvmax = 640, yvmax = 480, xwmax = 1280, ywmax = 960;

void myInit_window()
{
    glClearColor(1, 1, 1, 1.0);
    glColor3f(0.0f, 0.0f, 0.0f);
    glPointSize(3);
    glLineWidth(3);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(0.0, 1280.0, 0.0, 960.0);
}

void myInit_viewport()
{
    glClearColor(1, 1, 1, 1.0);
    glColor3f(0.0f, 0.0f, 0.0f);
    glPointSize(3);
    glLineWidth(3);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(0.0, 640.0, 0.0, 480.0);
}

void displayaxes_window()
{
    glBegin(GL_LINES);
    glColor4f(0, 0.5, 0, 1);
    // y - axis
    glVertex2d(640, 0);
    glVertex2d(640, 960);
    // x - axis glVertex2d(0,480);
    glVertex2d(1280, 480);
    glEnd();
}

void displayaxes_viewport()
{
    glBegin(GL_LINES);
    glColor4f(0, 0.5, 0, 1);
    // y - axis
    glVertex2d(320, 0);
    glVertex2d(320, 480);
    // x - axis
    glVertex2d(0, 240);
    glVertex2d(640, 240);
    glEnd();
}

void drawObject(int window)
{
    float x1, y1;
    cout << "Enter point 1 coordinates: ";
    cin >> x1 >> y1;
    float x2, y2;
    cout << "Enter point 2 coordinates: ";
    cin >> x2 >> y2;
    float x3, y3;
    cout << "Enter point 3 coordinates: ";
    cin >> x3 >> y3;
    if (window)
    {
        cout << "window\n";
        glBegin(GL_TRIANGLES);
        glColor4f(0.4, 0, 0.8, 1);
        glVertex2d(x1 + (xwmax / 2), y1 + (ywmax / 2));
        glVertex2d(x2 + (xwmax / 2), y2 + (ywmax / 2));
        glVertex2d(x3 + (xwmax / 2), y3 + (ywmax / 2));
        glEnd();
        glFlush();
    }
    else
    {
        cout << "viewport\n";
        float sx = xvmax / xwmax, sy = yvmax / ywmax;
        float S[3][3] = {{sx, 0, 0}, {0, sy, 0}, {0, 0, 1}};
        float T[3][3] = {{x1, y1, 1}, {x2, y2, 1}, {x3, y3, 1}};
        float R[3][3] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                for (int k = 0; k < 3; k++)
                {
                    R[i][j] += S[i][k] * T[k][j];
                }
            }
        }
        glBegin(GL_TRIANGLES);
        glColor4f(0, 0, 0.8, 1);
        glVertex2d(R[0][0] + (xvmax / 2), R[0][1] + (yvmax / 2));
        glVertex2d(R[1][0] + (xvmax / 2), R[1][1] + (yvmax / 2));
        glVertex2d(R[2][0] + (xvmax / 2), R[2][1] + (yvmax / 2));
        glEnd();
        glFlush();
    }
}

void plotWindow_window()
{
    myInit_window();
    glClear(GL_COLOR_BUFFER_BIT);
    displayaxes_window();
    drawObject(1);
    glFlush();
    glutSwapBuffers();
}

void plotWindow_viewport()
{
    myInit_viewport();
    glClear(GL_COLOR_BUFFER_BIT);
    displayaxes_viewport();
    drawObject(0);
    glFlush();
    glutSwapBuffers();
}

int main(int argc, char *argv[])
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGBA);
    glutInitWindowSize(xwmax, ywmax);
    int window = glutCreateWindow("Window");
    glutInitWindowSize(xvmax, yvmax);
    int viewport = glutCreateWindow("Viewport");
    glutSetWindow(window);
    glutDisplayFunc(plotWindow_window);
    glutSetWindow(viewport);
    glutDisplayFunc(plotWindow_viewport);
    glutMainLoop();
    return 1;
}