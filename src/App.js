import { Navbar } from "./app/components/Navbar/Navbar";
import { ApplicationScreen } from "./app/pages/application/ApplicationScreen";

function App() {
  return (
    <>
      {/* Navbar component */}
      <Navbar />

      {/* Main component */}
      <main role="main" className="container-fluid">
        <div className="pricing-header px-5 py-3 pt-md-2 pb-md-4 mx-auto text-center">
          {/* Applications table list */}
          <ApplicationScreen />
          <hr />
        </div>
      </main>
    </>
  );
}

export default App;
