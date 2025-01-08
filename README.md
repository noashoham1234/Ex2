# Ex2 - Excel Sheet Implementation

## Overview

This repository implements an Excel-like sheet with a range of functionalities. The sheet represents a grid from A-Z and 0-99, allowing users to input text, numbers, and formulas into cells.

## Features

- **Text, Number, and Formula Inputs**: Each cell can contain either:
  - A **text** (e.g., "a", "AB")
  - A **number** (e.g., `1`, `1.2`)
  - A **formula** that may perform mathematical calculations or refer to other cells. Examples of valid formulas include:
    - `=1`, `=1.2`, `=(0.2)`
    - `=1+2`, `=1+2*3`, `=(1+2)*((3))-1`
    - `=A1`, `=A2+3`, `=(2+A3)/A2`
    - `=2*3+(3*2)`, `=(A2*5)+(D18+2)*3`
  
- **Invalid Inputs**: Inputs that are not formulas, such as:
  - `a`, `AB`, `@2`, `2+)`, `(3+1*2)-`, `=()`, `=5**`

- **Error Handling**:
  - If a formula depends on a cell input and that cell is empty, the result will be a `FORM_ERR`.
  - If a formula depends on the current cell itself (creating a circular reference), it will return a `CYCLE_ERR`.

- **Color-Coded Inputs**: Different types of inputs (text, numbers, formulas) will be presented in different colors for easy identification.

- **File Support**: The Excel sheet can load and implement instructions from a `.txt` file, as well as save the current sheet to a file for later use.

## Usage

- **Text/Number/Formula Input**: You can input values directly into each cell. Valid formulas and calculations will be automatically computed.
  
- **Formula Syntax**: Use the `=` sign to start a formula. You can reference other cells, perform arithmetic operations, and use parentheses to control operation order.

- **Error Handling**: If a formula cannot be computed due to a missing input or circular reference, an appropriate error message (`FORM_ERR` or `CYCLE_ERR`) will be displayed.

## File Operations

- **Load from File**: You can load the sheet content from a `.txt` file that contains the instructions or data for the cells.
  
- **Save to File**: The current state of the sheet can be saved into a `.txt` file, retaining all inputs and calculations.

## Conclusion

This repository provides an Excel-like functionality with support for formulas, cell references, error handling, and file operations.
