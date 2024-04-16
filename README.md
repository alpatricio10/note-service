# Note Service

A simple note-taking API.

### Endpoints

Currently, the service has 5 working endpoints.

- #### POST /notes
  Create a new note. It has 

- #### GET /notes
  Retrieve all notes. 

- #### GET /notes/:id
  Retrieve a specific note by ID.

- #### PUT /notes/:id
  Update a specific note.

- #### DELETE /notes/:id
  Delete a specific note.

### Response

#### Successful Response (200 OK)

For the add, update and get specific note endpoints, the API returns the following JSON response:

```
{
    "id": "<id>",
    "title": "<title>",
    "body": "<body>"
}
```

For the get notes endpoint, the API returns a list of Note objects as follows:

```
{
    "notes": [
      {
        "id": "<id>",
        "title": "<title>",
        "body": "<body>"
      },
      ...
    ]
}
```

#### No Content Response (204 No Content)

Since the delete does not return anything, it gives a 204 status when it gets called successfully.

#### Error Response

For errors, the API sends the following JSON response:

```
{
  "code": "someCode",
  "message": "some error message"
}
```

Some known errors are:

- if the note identifier does not correspond to an existing note, then a *NotFound* error is returned.

### How to Run

```shell
# for running the application
$ mvn clean compile
$ mvn spring-boot:run
```

### Notes

- For the Note object, I only included the title and content fields as required, along with an identifier. In an actual API, I would choose to include other fields, including but not limited to create dates, update dates, the author, and maybe even tags. I just chose to make it as simple as possible for the purposes of this demo.
- For simplicity, I also used the Note object both as the representation of the entity, as well as the request object.
- I would usually have a separate repository class for data access, but since this is a small project, I keep it succinct by combining it in the service layer.
- Some possible features to consider - delete could be done softly (i.e. to cater to if notes can get restored after deletion), returning paginated views of the list, validating to restrict null or blank fields
- A postman collection is in the docs folder for easier testing

