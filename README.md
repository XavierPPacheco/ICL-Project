## Running
To run this project chmod +x the two .sh file in the BASE-0-2021/Base-0-2021/ directory.

After that, in order to use the interpreter run "./create_parser_interpreter_run" in the command line.
To use the compiler run "./create_parser_compiler_run" in the ccommand line.

### Additional Info
When running the compiler there are three flags available.
 * -f [FILENAME]. If the [Filename] contains a '/' then it fetches the file assuming the parameter passed was a directory and the user knows what it is doing. If no directory is passed then the filename is searched in the Examples directory. I.e -f boolean.icl is sanitized to './Examples/boolean.ic'l but '/boolean.icl' searches the root directory for a 'boolean.icl' file.
 * -c cleans the projectBin folder
 * -h displays the help menu.
### Autors
* Carolina Lopes - cma.lopes@campus.fct.unl.pt 53657
* Xavier Pacheco - x.pacheco@campus.fct.unl.pt 52748
