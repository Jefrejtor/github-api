# github-api

Application purpose:
The program is used to fetch select details about a given GitHub repository: full name, description, git clone URL, number of stargazers, date of creation.
Accessing the service:
GET /repositories/{owner}/{repository-name}
The details are then returned in a JSON format.
Program is specified to run on port 80.

Application structure:
Program consists of two classes, GitApp (main), in package "pax", and Ctrl, in package "pax.ctrl", second of which contains methods for parsing, serializing and deserializing JSON files. 