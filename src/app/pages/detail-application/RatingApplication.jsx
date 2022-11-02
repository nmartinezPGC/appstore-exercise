import React, { useContext } from 'react';
import ReactStars from 'react-rating-stars-component';
import Swal from 'sweetalert2';
import { ApplicationDetailContext } from '../../context/ApplicationDetailContext';
import { UserContext } from '../../context/UserContext';
import RatingService from '../../service/RatingService';

export const RatingApplication = () => {
    // Context declarations
    const { user } = useContext(UserContext);
    const { applicationDetail: { id_app, calificacion_app } } = useContext(ApplicationDetailContext);

    // Save Rating
    const saveRating = (newRating) => {
        const ratingData = {
            rating: newRating, emisor: user.name, id_app
        };
        RatingService.newRating({ ratingData })
            .then((transact) => {
                Swal.fire({
                    title: 'Calificación realizada',
                    text: 'Has registrado una calificación para esta aplicación!!!',
                    icon: 'success',
                });
            })
            .catch((err) => {
                console.log(err);
            });
    };

    return (
        <>
            <ReactStars
                count={5}
                onChange={saveRating}
                size={35}
                edit={true}
                value={calificacion_app}
                activeColor="#28a745"
            />
            <span className="badge badge-pill badge-success">{calificacion_app}</span>
        </>
    )
}
