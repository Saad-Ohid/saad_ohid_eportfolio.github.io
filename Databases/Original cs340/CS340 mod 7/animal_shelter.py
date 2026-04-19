# Import necessary libraries for MongoDB interaction
from pymongo import MongoClient, errors
from bson.objectid import ObjectId  # Used for handling MongoDB document IDs

class AnimalShelter(object):
    """
    CRUD operations for Animal collection in MongoDB.
    This class provides methods to create, read, update, and delete documents
    in the 'animals' collection of the 'aac' database.
    """

    def __init__(self, username, password, host, port):
        """
        Initializes the MongoClient and establishes a connection
        to the MongoDB server using predefined credentials.
        Handles connection errors gracefully.
        """
        USER = 'aacuser'
        PASS = 'SNHU1234'
        HOST = 'nv-desktop-services.apporto.com'
        PORT = 30261
        DB = 'AAC'
        COL = 'animals'

        # Initialize Connection
        try:
            self.client = MongoClient(f'mongodb://{username}:{password}@{host}:{port}/AAC')
            self.database = self.client[DB]  # Access the specified database
            self.collection = self.database[COL]  # Access the specified collection
            print("Connected successfully to MongoDB")
        except errors.ConnectionError as e:
            print(f"Could not connect to MongoDB: {e}")
            raise

    def create(self, data):
        """
        Inserts a new document into the 'animals' collection.
        Implements the 'C' (Create) operation of CRUD.
        """
        if data is not None:
            if isinstance(data, dict):
                try:
                    result = self.collection.insert_one(data)
                    if result.acknowledged:
                        print(f"Document inserted with ID: {result.inserted_id}")
                        return True
                    else:
                        print("Insertion not acknowledged by MongoDB.")
                        return False
                except errors.PyMongoError as e:
                    print(f"An error occurred while inserting data: {e}")
                    return False
            else:
                raise ValueError("Data parameter must be a dictionary.")
        else:
            raise ValueError("Data parameter is empty (None). Nothing to save.")

    def read(self, query):
        """
        Reads documents from the 'animals' collection based on a query.
        Implements the 'R' (Read) operation of CRUD.
        """
        if query is not None:
            if isinstance(query, dict):
                try:
                    documents = list(self.collection.find(query))
                    print(f"Found {len(documents)} document(s) matching the query.")
                    return documents
                except errors.PyMongoError as e:
                    print(f"An error occurred while reading data: {e}")
                    return []
            else:
                raise ValueError("Query parameter must be a dictionary.")
        else:
            raise ValueError("Query parameter is empty (None).")

    def update(self, query, update_data):
        """
        Updates documents in the 'animals' collection based on a query.
        Implements the 'U' (Update) operation of CRUD.
        """
        if query is not None and update_data is not None:
            if isinstance(query, dict) and isinstance(update_data, dict):
                try:
                    result = self.collection.update_many(query, {'$set': update_data})
                    print(f"Modified {result.modified_count} document(s).")
                    return result.modified_count
                except errors.PyMongoError as e:
                    print(f"An error occurred while updating data: {e}")
                    return 0
            else:
                raise ValueError("Both query and update_data parameters must be dictionaries.")
        else:
            raise ValueError("Query and update_data parameters cannot be empty (None).")

    def delete(self, query):
        """
        Deletes documents from the 'animals' collection based on a query.
        Implements the 'D' (Delete) operation of CRUD.
        """
        if query is not None:
            if isinstance(query, dict):
                try:
                    result = self.collection.delete_many(query)
                    print(f"Deleted {result.deleted_count} document(s).")
                    return result.deleted_count
                except errors.PyMongoError as e:
                    print(f"An error occurred while deleting data: {e}")
                    return 0
            else:
                raise ValueError("Query parameter must be a dictionary.")
        else:
            raise ValueError("Query parameter cannot be empty (None).")
