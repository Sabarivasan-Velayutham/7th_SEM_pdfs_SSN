#include <stdio.h>
#include <iostream>
#include <GL/glut.h>
#include <cmath>

using namespace std;

float angleSeconds = 0.0;
float angleMinutes = 0.0;
float angleHours = 0.0;

void myInit(void)
{   
    glClearColor(1.0, 1.0, 1.0, 0.0);
    glColor3f(0.0f, 0.0f, 0.0f);
    glPointSize(4.0);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(-320.0, 320.0, -240.0, 240.0);
}

void plot(int x, int y, int pntx, int pnty)
{
    glBegin(GL_POINTS);
    glVertex2i(x + pntx, y + pnty);
    glEnd();
}

void midPointCircleAlgo(int pntx, int pnty, int radius)
{
    int x = 0;
    int y = radius;
    float decision = 5 / 4 - radius;
    plot(x, y, pntx, pnty);
    while (y > x)
    {
        if (decision < 0)
        {
            x++;
            decision += 2 * x + 1;
        }
        else
        {
            y--;
            x++;
            decision += 2 * (x - y) + 1;
        }
        plot(x, y, pntx, pnty);
        plot(x, -y, pntx, pnty);
        plot(-x, y, pntx, pnty);
        plot(-x, -y, pntx, pnty);
        plot(y, x, pntx, pnty);
        plot(-y, x, pntx, pnty);
        plot(y, -x, pntx, pnty);
        plot(-y, -x, pntx, pnty);
    }
}

void drawHand(float length, float angle)
{
    float x = length * cos(angle);
    float y = length * sin(angle);
    glBegin(GL_LINES);
    glVertex2f(0, 0);
    glVertex2f(x, y);
    glEnd();
}

void myDisplay(void)
{
    glClear(GL_COLOR_BUFFER_BIT);
    glColor3f(0.0, 0.0, 0.0);
    glPointSize(1.0);

    midPointCircleAlgo(0, 0, 150); // Clock outline

    // Draw clock hands
    drawHand(120, angleHours);
    drawHand(140, angleMinutes);
    drawHand(145, angleSeconds);

    glutSwapBuffers();
}

void updateClockHands()
{
    // Increment angles for clock hands
    angleHours += 0.0001; // Adjust the speed of rotation if needed
    angleMinutes += 0.001;
    angleSeconds += 0.1;

    // Reset angles when they exceed 360 degrees
    if (angleHours > 360)
        angleHours = 0;
    if (angleMinutes > 360)
        angleMinutes = 0;
    if (angleSeconds > 360)
        angleSeconds = 0;
}

void update(int value)
{
    updateClockHands();
    glutPostRedisplay();
    glutTimerFunc(16, update, 0); // 60 frames per second
}

int main(int argc, char **argv)
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
    glutInitWindowSize(640, 480);
    glutInitWindowPosition(100, 150);
    glutCreateWindow("Analog Clock with Animation");
    glutDisplayFunc(myDisplay);
    myInit();
    glutTimerFunc(25, update, 0); // Timer function to update the clock hands
    glutMainLoop();
    return 0;
}
