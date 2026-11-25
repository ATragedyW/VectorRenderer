
# VectorEngine

A lightweight 3D software rendering engine built from
scratch in Java without external 3D graphics libraries.

VectorEngine implements fundamental computer graphics
algorithms, mathematical transformations, lighting,
and interactive 3D model rendering.

## Features

- Custom 3D vector mathematics
- Indexed mesh geometry
- Real-time object rotation
- Perspective projection
- Interactive camera controls
- Solid polygon rendering
- Painter's Algorithm for depth sorting
- Lambertian lighting and shading
- Wavefront OBJ model importing
- Interactive model file selection

## Technologies

- Java
- Java Swing
- Java AWT
- Object-Oriented Programming
- Linear Algebra
- Data Structures and Algorithms
- Git and GitHub

## Project Architecture

| File | Responsibility |
|---|---|
| Main.java | Application entry point |
| Renderer.java | Rendering, lighting and camera controls |
| Vector3.java | 3D vector mathematics |
| Mesh.java | Geometry and mesh representation |
| OBJLoader.java | OBJ file parsing |
| pyramid.obj | Sample 3D model |

## Getting Started

### Requirements

- Java Development Kit (JDK 21 recommended)

### Compile and Run

```bash
javac *.java
java Main
```

## Controls

| Key | Action |
|---|---|
| W | Move camera closer |
| S | Move camera farther away |
| A | Move camera left |
| D | Move camera right |
| Space | Pause or resume rotation |
| O | Open an OBJ model |
| C | Restore default cube |

## Technical Concepts

This project explores the underlying principles of
computer graphics and game engine development:

- Vector addition and subtraction
- Scalar multiplication
- Dot and cross products
- Vector normalization
- 3D coordinate transformations
- Perspective projection
- Surface normal calculations
- Polygon depth sorting
- File I/O and parsing
- Event-driven programming
- Object-oriented architecture

## Current Limitations

- Basic OBJ geometry support
- No texture or material importing
- Painter's Algorithm instead of per-pixel depth buffering
- No near-plane clipping

## Future Development

- Depth buffering
- Triangle rasterization
- Texture mapping
- Multiple objects per scene
- Free camera movement
- Scene management
- Model scaling and positioning
- Performance optimization

## Purpose

VectorEngine was developed as an educational software
engineering project to explore how rendering engines
operate without relying on existing 3D game engines.
