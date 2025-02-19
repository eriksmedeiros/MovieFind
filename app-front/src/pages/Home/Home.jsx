import axios from 'axios'
import { useEffect, useState } from 'react'

import './Home.css'

const API_URL = import.meta.env.VITE_API_URL;

const Home = () => {

  const [movies, setMovies] = useState([]);

  const getMovies = async () => {
    try {
      const response = await axios.get(API_URL + '/api/movies');
      setMovies(response.data);


    } catch (error) {
      console.log(error);
    }
  }

  useEffect(() => {
    getMovies();
  }, []);

  return (
    <div>
      <h1>Filmes</h1>
      {movies.length === 0 ? <p>Carregando</p> : (movies.map((movie) => (
          <div className="movie" key={movie.id}>
            <h2>{movie.title}</h2>
            <p>{movie.genre}</p>
            <p>{movie.director}</p>
            <p>{movie.synopsis}</p>
            <p>{movie.releaseDate}</p>
            <p>{movie.duration}</p>
          </div>
      ))
      )}
    </div>
  );
};

export default Home