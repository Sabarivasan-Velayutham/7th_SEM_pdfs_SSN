
#include <GL/glut.h>
#include <iostream>
#include <cmath>
#include <string>

using namespace std;

void drawString(float x, float y, const char *string)
{
    glRasterPos2f(x, y);
    for (const char *c = string; *c != '\0'; c++)
        glutBitmapCharacter(GLUT_BITMAP_HELVETICA_12, *c);
}

void myInit()
{
    glClearColor(1.0, 0.6, 0.5, 0.0);
    glPointSize(1);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(0.0, 480.0, 0.0, 480.0);
}

void myDisplay()
{
    glClear(GL_COLOR_BUFFER_BIT);
    int x1, y1, x2, y2, dx, dy, choice;
    float x, y;
    cout << "Enter 1 for L->R (or) 2 for R->L : ";
    cin >> choice;
    cout << "Enter x1, y1, x2, y2: ";
    cin >> x1 >> y1 >> x2 >> y2;
    glColor3f(0.0, 0.0, 0.0);
    glBegin(GL_POINTS);
    glVertex2f(x1, y1);
    glEnd();
    dx = x2 - x1;
    dy = y2 - y1;
    float m = (float)dy / dx;
    cout << "Slope = " << m << endl;
    /* Positive Slope L->R */
    if (choice == 1 && m >= 0.0)
    {
        x = x1;
        y = y1;
        if (abs(m) <= 1.0)
        {
            while (x != x2)
            {
                y = y + m;
                x += 1;
                glBegin(GL_POINTS);
                glVertex2f(x, round(y));
                glEnd();
            }
        }
        else
        {
            while (y != y2)
            {
                x = x + (1 / m);
                y += 1;
                glBegin(GL_POINTS);
                glVertex2f(round(x), y);
                glEnd();
            }
        }
    }
    /* Positive Slope R->L */
    else if (choice == 2 && m >= 0)
    {
        x = x2;
        y = y2;
        if (abs(m) <= 1.0)
        {
            while (x != x1)
            {
                y = y - m;
                x -= 1;
                glBegin(GL_POINTS);
                glVertex2f(x, round(y));
                glEnd();
            }
        }
        else
        {
            while (y != y1)
            {
                x = x - (1 / m);
                y -= 1;
                glBegin(GL_POINTS);
                glVertex2f(round(x), y);
                glEnd();
            }
        }
    }
    /* Negative Slope L->R */
    else if (choice == 1 && m < 0)
    {
        x = x1;
        y = y1;
        if (abs(m) <= 1.0)
        {
            while (x != x2)
            {
                y = y - abs(m);
                x += 1;
                glBegin(GL_POINTS);
                glVertex2f(x, round(y));
                glEnd();
            }
        }
        else
        {
            while (y != y2)
            {
                x = x + (1 / m);
                y -= 1;
                glBegin(GL_POINTS);
                glVertex2f(round(x), y);
                glEnd();
            }
        }
    }
    else if (choice == 2 && m < 0)
    {
        x = x2;
        y = y2;
        if (abs(m) <= 1.0)
        {
            while (x != x1)
            {
                y = y + abs(m);
                x -= 1;
                glBegin(GL_POINTS);
                glVertex2f(x, round(y));
                glEnd();
            }
        }
        else
        {
            while (y != y1)
            {
                x = x - abs(1 / m);
                y += 1;
                glBegin(GL_POINTS);
                glVertex2f(round(x), y);
                glEnd();
            }
        }
    }
    drawString(x1, y1, ("(" + to_string(x1) + "," + to_string(y1) + ")").c_str());
    drawString(x2, y2, ("(" + to_string(x2) + "," + to_string(y2) + ")").c_str());
    glFlush();
}

int main(int argc, char *argv[])
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowSize(480, 480);
    glutCreateWindow("DDA Algorithm");
    glutDisplayFunc(myDisplay);
    myInit();
    glutMainLoop();
    return 1;
}