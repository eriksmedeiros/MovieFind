import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from "axios";
import "./Movie.css";

const API_URL = import.meta.env.VITE_API_URL;

const Movie = () => {

  const {title} = useParams();
  const [movie, setMovie] = useState([]);

  const getMovies = async () => {
    try {
      const response = await axios.get(API_URL + '/search');
      setMovie(response.data);
    } catch (error) {
      console.log(error);
    }
  }

  useEffect(() => {
    getMovies();
  }, [title]);

  if (!movie) {
    return <p>Carregando</p>
  }

  return (
    <div className="movie">
      <h1>{movie.title}</h1>
      <img src={movie.poster_path} alt={movie.title} />
      <p>Nota: {movie.vote_average}</p>
    </div>
  )
}

export default Movie