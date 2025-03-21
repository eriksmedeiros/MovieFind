import {useParams} from 'react-router-dom';
import {useEffect, useState} from 'react';
import axios from 'axios';

import './Movie.css';

const API_URL = import.meta.env.VITE_API_URL;

const Movie = () => {
  const {id} = useParams();
  console.log("ID do filme:", id);
  const [movie, setMovie] = useState(null);

  const getMovies = async (url) => {
      const response = await axios.get(url);
      console.log("Dados recebidos:", response.data);
      setMovie(response.data);
  }

  // const formatCurrency = (number) => {
  //     return number.toLocaleString("en-US", {
  //         style: "currency",
  //         currency: "USD"
  //     })
  // }

  useEffect(() => {
    if (id) { 
      const url = `${API_URL}/api/movies/${id}`;
      console.log("Buscando dados na URL:", url);
      getMovies(url);
    }
  }, [id, API_URL]);
  

  console.log(movie);

  return (
    <div className="movie-page">
      <h1>Filmes</h1>
      {!movie ? (
        <p>Carregando...</p> // 🔥 Exibe uma mensagem enquanto os dados não chegam
      ) : (
        <>
          <h1>{movie.title}</h1>
          <img src={`https://image.tmdb.org/t/p/w500${movie.poster_path}`} alt={movie.title} />
        </>
      )}
    </div>
  );
}

export default Movie;