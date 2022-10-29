import "./App.css";
import { Navbar } from "./app/components/Navbar/Navbar";

function App() {
  return (
    <>
      {/* Navbar component */}
      <Navbar />

      {/* Main component */}
      <main role="main" class="container">
        <div class="starter-template">
          <img src="img/meme.jpg" alt="" srcset="" />
          <p class="lead">Éxitos</p>
        </div>
      </main>
    </>
  );
}

export default App;
