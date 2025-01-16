#include <GL/glut.h>
#include <cmath>

float pendulumLength = 150.0;  // Length of the pendulum arm
float pendulumAngle = 45.0;   // Initial angle of the pendulum
int direction = 1;            // 1 for forward swing, -1 for backward swing

void drawPendulum() {
    glBegin(GL_LINES);
    glVertex2f(200.0, 300.0);  // Fixed point
    glVertex2f(200.0 + pendulumLength * sin(pendulumAngle * 3.14159 / 180),
               300.0 - pendulumLength * cos(pendulumAngle * 3.14159 / 180));
    glEnd();
}

void display() {
    glClear(GL_COLOR_BUFFER_BIT);
    glColor3f(0.0, 0.0, 0.0);  // Set color to black

    // Draw the pendulum
    drawPendulum();

    glutSwapBuffers();
}

void update(int value) {
    // Update the pendulum angle for animation
    pendulumAngle += 1.0 * direction;

    // Check if the pendulum has swung to its maximum angle
    if (pendulumAngle > 135.0 || pendulumAngle < 45.0) {
        // Change the direction of the swing
        direction *= -1;
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
    glutCreateWindow("Pendulum Animation");
    glutDisplayFunc(display);
    myInit();
    glutTimerFunc(25, update, 0);  // Set the timer for the first update after 25 milliseconds
    glutMainLoop();
    return 0;
}
