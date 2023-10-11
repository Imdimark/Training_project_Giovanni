// src/PersonsList.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';

function PersonsList() {
  const [persons, setPersons] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/company/getPersons')
      .then(response => {
        console.log("pippo");
        console.log("Response Data:", response.data);
        setPersons(response.data);
      })
      .catch(error => {
        console.log('Errore durante la chiamata GET:', error);
      });
  }, []);

  return (
    <div>
      <h2>Elenco Persone</h2>
      <ul>
        {persons.map(person => (
          <li key={person.id}>{person.name} {person.surName} {person.department}</li> 
          // Assicurati che 'id' e 'fullName' siano attributi corretti dell'oggetto 'person'.
        ))}
      </ul>
    </div>
  );
}

export default PersonsList;
