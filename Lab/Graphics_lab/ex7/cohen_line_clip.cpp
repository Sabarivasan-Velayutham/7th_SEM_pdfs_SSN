

#include <iostream>
#include <GL/glut.h>
using namespace std;

void myInit()
{
    glClearColor(1, 1, 1, 0.0);
    glColor3f(1.0f, 1.0f, 1.0f);
    glPointSize(3);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(0.0, 640.0, 0.0, 480.0);
}

// Defining region codes
const int INSIDE = 0; // 0000
const int LEFT = 1;   // 0001
const int RIGHT = 2;  // 0010
const int BOTTOM = 4; // 0100
const int TOP = 8;    // 1000

// Defining x_max, y_max and x_min, y_min for
// clipping rectangle. Since diagonal points are
// enough to define a rectangle
const int x_max = 400;
const int y_max = 400;
const int x_min = 200;
const int y_min = 200;

// Function to compute region code for a point(x, y)
int computeCode(double x, double y)
{
    // initialized as being inside
    int code = INSIDE;
    if (x < x_min) // to the left of rectangle
        code |= LEFT;
    else if (x > x_max) // to the right of rectangle
        code |= RIGHT;
    if (y < y_min) // below the rectangle
        code |= BOTTOM;
    else if (y > y_max) // above the rectangle
        code |= TOP;
    return code;
}

// Implementing Cohen-Sutherland algorithm
// Clipping a line from P1 = (x2, y2) to P2 = (x2, y2)
void lineclip(double x1, double y1,
              double x2, double y2)
{
    // Compute region codes for P1,P2
    int code1 = computeCode(x1, y1);
    int code2 = computeCode(x2, y2);
    // Initialize line as outside the rectangular window
    bool accept = false;
    while (true)
    {
        if ((code1 == 0) && (code2 == 0))
        {
            // If both endpoints lie within rectangle
            accept = true;
            break;
        }
        else if (
            code1 &
            code2)
        {
            // If both endpoints are outside rectangle,
            // in same region
            break;
        }
        else
        {
            // Some segment of line lies within the
            // rectangle
            int code_out;
            double x = 0, y = 0;
            // At least one endpoint is outside the
            // rectangle, pick
            if (code1 != 0)
                code_out = code1;
            else
                code_out = code2;
            // Find intersection point;
            // using formulas y = y1 + slope * (x - x1),
            // x = x1 + (1 / slope) * (y - y1)
            if (code_out & TOP)
            {
                // point is above the clip rectangle
                x = x1 + (x2 - x1) * (y_max - y1) / (y2 - y1);
                y = y_max;
            }
            else if (
                code_out &
                BOTTOM)
            {
                // point is below the rectangle
                x = x1 + (x2 - x1) * (y_min - y1) / (y2 - y1);
                y = y_min;
            }
            else if (
                code_out &
                RIGHT)
            {
                // point is to the right of rectangle
                y = y1 + (y2 - y1) * (x_max - x1) / (x2 - x1);
                x = x_max;
            }
            else if (
                code_out &
                LEFT)
            {
                // point is to the left of rectangle
                y = y1 + (y2 - y1) * (x_min - x1) / (x2 - x1);
                x = x_min;
            }
            // Now intersection point x, y is found
            // We replace point outside rectangle
            // by intersection point
            if (code_out == code1)
            {
                x1 = x;
                y1 = y;
                code1 = computeCode(x1, y1);
            }
            else
            {
                x2 = x;
                y2 = y;
                code2 = computeCode(x2, y2);
            }
        }
    }
    if (accept)
    {
        cout << "Line accepted from " << x1 << ", "
             << y1 << " to " << x2 << ", " << y2 << endl;
        glBegin(GL_LINES);
        glColor3f(0.5f, 0.1f, 0.5f);
        glVertex2d(x1, y1);
        glVertex2d(x2, y2);
        glEnd();
        glFlush();
    }
    else
        cout << "Line rejected" << endl;
}

// Driver code
void myDisplay()
{
    cout << "The clipping window has been set with the coordinates:\n";
    cout << "(200,200),(200,400),(400,400) and (400,200)\n\n";
    // First Line segment
    int x1, x2, y1, y2;
    cout << "Enter (x1,y1):";
    cin >> x1 >> y1;
    cout << "Enter (x2,y2):";
    cin >> x2 >> y2;
    glFlush();
    glClear(GL_COLOR_BUFFER_BIT);

    glBegin(GL_LINES);
    glColor3f(0.5f, 0.1f, 0.1f);
    glVertex2d(200, 200);
    glVertex2d(200, 400);
    glEnd();
    glFlush();

    glBegin(GL_LINES);
    glColor3f(0.5f, 0.1f, 0.1f);
    glVertex2d(200, 400);
    glVertex2d(400, 400);
    glEnd();
    glFlush();

    glBegin(GL_LINES);
    glColor3f(0.5f, 0.1f, 0.1f);
    glVertex2d(400, 400);
    glVertex2d(400, 200);
    glEnd();
    glFlush();

    glBegin(GL_LINES);
    glColor3f(0.5f, 0.1f, 0.1f);
    glVertex2d(400, 200);
    glVertex2d(200, 200);
    glEnd();
    glFlush();

    // P11 = (5, 5), P12 = (7, 7)
    glBegin(GL_POINTS);
    glVertex2d(x1, y1);
    glEnd();
    glFlush();

    glBegin(GL_POINTS);
    glVertex2d(x2, y2);
    glEnd();
    glFlush();

    lineclip(x1, y1, x2, y2);
    // Second Line segment
    // P21 = (7, 9), P22 = (11, 4)
    // lineclip(7,9, 11, 4);;
    
    // Third Line segment
    // P31 = (1, 5), P32 = (4, 1)
    // lineclip(1, 5, 4, 1);
}

int main(int argc, char *argv[])
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowSize(640, 480);
    glutCreateWindow("Line Clipping");
    glutDisplayFunc(myDisplay);
    myInit();
    glutMainLoop();
    return 1;
}