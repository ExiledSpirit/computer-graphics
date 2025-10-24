#include <stdlib.h>
#include <stdio.h>
#include <GLFW/glfw3.h>
#include <cmath>

GLFWmonitor* monitor;
GLFWvidmode* mode;
GLFWwindow *window, *mainWindow;

void render();
void recreate();
void drawSphere();

static void error(int error, const char* description)
{
    fputs(description, stderr);
}

int main()
{
    glfwSetErrorCallback(error);

    if (!glfwInit())
        return -1;
    
    monitor = glfwGetPrimaryMonitor();
    mode = (GLFWvidmode*)glfwGetVideoMode(monitor);

    glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
    mainWindow = glfwCreateWindow(1, 1, "0", NULL, NULL);
    
    recreate();
    
    while(!glfwWindowShouldClose(window))
    {
        render();
        glfwPollEvents();
    }

    glfwTerminate();
    return 0;
}

void render()
{
    glfwSwapBuffers(window);
    drawCircle(250, 250, 100, 360);
}

void recreate()
{
    window = glfwCreateWindow(800, 600, "Local Illumination Shader", NULL, mainWindow);
    glfwDestroyWindow(mainWindow);
    mainWindow = window;
    glfwMakeContextCurrent(mainWindow);
    glfwShowWindow(mainWindow);
}

void drawSphere() {

}
