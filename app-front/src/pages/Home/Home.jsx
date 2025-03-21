import axios from 'axios'
import { useEffect, useState } from 'react'
import MovieCard from '../../components/MovieCard/MovieCard'

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
    <div className="home">
      <h1>Filmes</h1>
      {movies.length === 0 ? <p>Carregando</p> : (movies.map((movie, index) => (
          <MovieCard key={movie.id || index} 
            id={movie.id}
            poster_path={movie.poster_path}
            title={movie.title}
            vote_average={movie.vote_average}
          />
      ))
      )}
    </div>
  );
};

export default Home