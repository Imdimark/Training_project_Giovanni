// src/App.js
import React from 'react';
import './App.css';
import PersonsList from './PersonsList';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Benvenuti nella mia azienda</h1>
      </header>
      <main>
        <PersonsList />
      </main>
    </div>
  );
}

export default App;






/*import React from 'react';
import logo from './logo.svg';
import './App.css';
import CompanyComponent from './components/CompanyComponent';

function App() {
  return (
    <div>
      <header className="container">
         <CompanyComponent />
      </header>
    </div>
  );
}

export default App;



import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;*/
