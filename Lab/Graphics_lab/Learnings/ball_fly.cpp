#include <GL/glut.h>
#include <math.h>

float circleX = 50.0;  // Initial X position of the circle

void drawCircle(float x, float y, float radius) {
    glBegin(GL_TRIANGLE_FAN);
    for (int i = 0; i < 360; i++) {
        float angle = i * 3.14159 / 180;
        float cx = x + radius * cos(angle);
        float cy = y + radius * sin(angle);
        glVertex2f(cx, cy);
    }
    glEnd();
}

void display() {
    glClear(GL_COLOR_BUFFER_BIT);
    glColor3f(0.0, 0.0, 0.0);  // Set color to black

    // Draw the moving circle
    drawCircle(circleX, 200.0, 20.0);

    glutSwapBuffers();
}

void update(int value) {
    // Update the X position for animation
    circleX += 2.0;

    // Check if the circle has moved off the right edge
    if (circleX > 380.0) {
        circleX = 50.0;  // Reset to the initial position
    }

    glutPostRedisplay();  // Trigger a redraw
    glutTimerFunc(16, update, 0);  // Set the next update after 16 milliseconds
}

void myInit() {
    glClearColor(1.0, 1.0, 1.0, 0.0);  // Set background color to white
    gluOrtho2D(0.0, 400.0, 0.0, 400.0);
}

int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
    glutInitWindowSize(400, 400);
    glutCreateWindow("Moving Circle Animation");
    glutDisplayFunc(display);
    myInit();
    glutTimerFunc(25, update, 0);  // Set the timer for the first update after 25 milliseconds
    glutMainLoop();
    return 0;
}
