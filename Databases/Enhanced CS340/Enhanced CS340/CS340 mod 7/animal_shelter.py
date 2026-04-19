# =============================================================================
# MODULE: animal_shelter.py
# DESCRIPTION: 
#   This module provides the AnimalShelter class to perform robust CRUD 
#   operations on a MongoDB database. It includes data validation and 
#   error handling as part of the Category Three: Databases enhancement.
#
# VERSION: 3.0 (Milestone 4 - Final Enhancement)
# =============================================================================

from pymongo import MongoClient, errors
from bson.objectid import ObjectId
import config  # Importing decoupled credentials for security

class AnimalShelter(object):
    """
    CRUD operations for the Animal collection in MongoDB.
    
    This class serves as the data access layer for the animal shelter system.
    It manages the connection to MongoDB and provides a high-level API for 
    interacting with animal records while ensuring data integrity.
    """

    def __init__(self, username=None, password=None, host=None, port=None):
        """
        Initializes the connection to the MongoDB server.
        
        Proper Handling Implementation:
        - Utilizes a configuration file to avoid hardcoding credentials.
        - Handles connection errors gracefully using PyMongo exceptions.
        - Supports both local (no-auth) and authenticated database connections.
        """
        # Load connection settings from the configuration module
        USER = config.DB_USER
        PASS = config.DB_PASS
        HOST = config.DB_HOST
        PORT = config.DB_PORT
        DB = config.DB_NAME
        COL = config.DB_COLLECTION

        try:
            # Construct the connection string based on the presence of credentials
            if USER and PASS:
                # Format for authenticated connection
                self.client = MongoClient(f"mongodb://{USER}:{PASS}@{HOST}:{PORT}/{DB}")
            else:
                # Format for local unauthenticated connection
                self.client = MongoClient(f"mongodb://{HOST}:{PORT}")
            
            # Establish access to the specific database and collection
            self.database = self.client[DB]
            self.collection = self.database[COL]
            
            # Print status to console for system verification
            print(f"System: Successfully connected to {DB}.{COL} at {HOST}:{PORT}.")
            
        except errors.ConnectionError as ce:
            print(f"Initialization Error: Could not connect to MongoDB server. Detail: {ce}")
            raise
        except errors.PyMongoError as pe:
            print(f"Initialization Error: A database driver error occurred. Detail: {pe}")
            raise
        except Exception as e:
            print(f"Initialization Error: An unexpected error occurred. Detail: {e}")
            raise

    def create(self, data):
        """
        Inserts a new document into the animals collection.
        
        Proper Handling Implementation:
        - Schema Validation: Checks for required fields ('breed', 'animal_type', 'name').
        - Data Integrity: Rejects junk data or empty strings for required keys.
        - Exception Safety: Wraps the write operation in a try-except block.
        
        Returns:
            bool: True if the document was successfully inserted, False otherwise.
        """
        # Step 1: Basic input validation
        if data is None:
            print("Create Failure: No data provided to the create operation.")
            return False
            
        if not isinstance(data, dict):
            print("Create Failure: Provided data must be a dictionary.")
            return False

        # Step 2: Schema Validation (Data Integrity Enhancement)
        # Ensure that every record has the minimum required information
        required_fields = ['animal_type', 'breed', 'name']
        for field in required_fields:
            if field not in data or not str(data[field]).strip():
                print(f"Validation Failure: The field '{field}' is required and cannot be empty.")
                return False

        # Step 3: Database interaction with error handling
        try:
            # Perform the insert operation
            result = self.collection.insert_one(data)
            
            # Verify that the database acknowledged the insertion
            if result.acknowledged:
                print(f"Create Success: Document ID {result.inserted_id} saved to database.")
                return True
            else:
                print("Create Failure: The database did not acknowledge the insert operation.")
                return False
                
        except errors.WriteError as we:
            print(f"Database Error: A write error occurred. Detail: {we}")
            return False
        except errors.PyMongoError as pe:
            print(f"Database Error: A general MongoDB error occurred. Detail: {pe}")
            return False

    def read(self, query):
        """
        Retrieves records from the collection matching the query filter.
        
        Proper Handling Implementation:
        - Validates query parameter types.
        - Converts the MongoDB cursor into a list for easier data manipulation.
        - Handles retrieval errors to prevent dashboard crashes.
        
        Returns:
            list: A list of documents found, or an empty list if an error occurs.
        """
        # Step 1: Parameter validation
        if query is None:
            print("Read Failure: Query parameter cannot be None.")
            return []
            
        if not isinstance(query, dict):
            print("Read Failure: Query must be provided as a dictionary.")
            return []

        # Step 2: Database interaction
        try:
            # Execute the query
            cursor = self.collection.find(query)
            
            # Materialize the cursor into a list for the calling application
            results = list(cursor)
            
            print(f"Read Success: Found {len(results)} matching record(s).")
            return results
            
        except errors.PyMongoError as pe:
            print(f"Database Error: Failed to read from the collection. Detail: {pe}")
            return []

    def update(self, query, update_data):
        """
        Modifies documents in the collection matching the filter.
        
        Proper Handling Implementation:
        - Validates that both query and update_data are dictionaries.
        - Uses update_many with the '$set' operator to prevent data loss.
        - Returns the count of modified documents for the UI feedback.
        
        Returns:
            int: The number of documents successfully modified.
        """
        # Step 1: Parameter validation
        if query is None or update_data is None:
            print("Update Failure: Missing required query or update data parameters.")
            return 0
            
        if not isinstance(query, dict) or not isinstance(update_data, dict):
            print("Update Failure: Query and update data must be dictionaries.")
            return 0

        # Step 2: Database interaction
        try:
            # Execute the update using $set to modify only specific fields
            result = self.collection.update_many(query, {"$set": update_data})
            
            print(f"Update Success: {result.modified_count} record(s) were modified.")
            return result.modified_count
            
        except errors.PyMongoError as pe:
            print(f"Database Error: Failed to update the collection. Detail: {pe}")
            return 0

    def delete(self, query):
        """
        Removes documents from the collection matching the filter.
        
        Proper Handling Implementation:
        - Validates query type and presence.
        - Uses delete_many to handle multiple deletions safely.
        - Includes error handling to catch permission or connection issues.
        
        Returns:
            int: The number of documents removed from the database.
        """
        # Step 1: Parameter validation
        if query is None:
            print("Delete Failure: Delete query cannot be None.")
            return 0
            
        if not isinstance(query, dict):
            print("Delete Failure: Delete query must be a dictionary.")
            return 0

        # Step 2: Database interaction
        try:
            # Execute the deletion operation
            result = self.collection.delete_many(query)
            
            print(f"Delete Success: {result.deleted_count} record(s) were removed.")
            return result.deleted_count
            
        except errors.PyMongoError as pe:
            print(f"Database Error: Failed to delete records. Detail: {pe}")
            return 0

# =============================================================================
# END OF MODULE
# =============================================================================