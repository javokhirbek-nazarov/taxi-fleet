import {RouterProvider} from 'react-router-dom';

import createRouter from './router/Router';
import Header from "./components/Header";

function App() {
  const router = createRouter();

  return (
      <div className="App">
        <Header/>
        <RouterProvider router={router}></RouterProvider>
      </div>
  );
}

export default App;
