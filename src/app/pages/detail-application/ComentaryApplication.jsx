import React, { useContext, useEffect, useState } from 'react';
import { ApplicationDetailContext } from '../../context/ApplicationDetailContext';
import ApplicationService from '../../service/ApplicationService';

export const ComentaryApplication = () => {
    // Context declarations
    const { applicationDetail: { id_app } } = useContext(ApplicationDetailContext);

    // State declarations
    const [comentaries, setComentaries] = useState([]);

    // Function All Comentaries of Applications
    const getDetailApplication = async (application) => {
        await ApplicationService.getDetailApplication(application)
            .then((resp) => {
                const { data } = resp;
                // Cambia el estado
                setComentaries(data.comentaries);
            })
            .catch((err) => {
                alert(err);
            });
    };

    // Excecute seach Comentaries
    useEffect(() => {
        getDetailApplication(id_app);
    }, [id_app]);


    return (
        <>
            <div className="list-group">
                {comentaries.map((comments) => (
                    <a key={comments.id} href="#/" className="list-group-item-action flex-column text-left">
                        <div className="d-flex w-100 justify-content-between">
                            <div className="col-md-2">
                                <img className="rounded-circle card-img-top"
                                    src={process.env.PUBLIC_URL + '/img/user.webp'} alt={"User"} />
                            </div>
                            <div className="col-md-10">
                                <h6 className="mb-1">{comments.emisor}</h6>
                                <p className="mb-1">{comments.description}</p>
                                {/* <small>Fecha de comentario</small> */}
                            </div>
                        </div>
                        <hr />
                    </a>
                ))}
            </div>
        </>
    )
}
