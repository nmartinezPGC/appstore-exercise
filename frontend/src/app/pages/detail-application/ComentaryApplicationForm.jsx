import React, { useContext, useState } from 'react';
import { ApplicationDetailContext } from '../../context/ApplicationDetailContext';
import { UserContext } from '../../context/UserContext';
import ComentaryService from '../../service/ComentaryService';
import Swal from 'sweetalert2';

export const ComentaryApplicationForm = () => {
    // Context declarations
    const { user } = useContext(UserContext);
    const { applicationDetail: { id_app } } = useContext(ApplicationDetailContext);

    // State section
    const [formComentary, setComentary] = useState({
        description: "",
        emisor: user.name,
        id_app: id_app
    });
    const { description, emisor } = formComentary;

    const [isActive, setIsActive] = useState(true);

    // Methods of Form
    const handleInputChange = (event) => {
        setComentary({
            ...formComentary,
            [event.target.name]: event.target.value,
        });

        if (description.length > 0) {
            setIsActive(false);
            return;
        } else {
            setIsActive(true);
            return;
        }
    };

    // Save Comentary
    const saveComentary = (event) => {
        event.preventDefault();

        if (!isActive && description.length === 0) {
            alert('Tienes que ingresar una descripción de tu mensaje!!!!!');
            return;
        }

        const comentaryData = {
            description, emisor, id_app
        };
        ComentaryService.newComentary({ comentaryData })
            .then((transact) => {
                // handles
                setIsActive(true);
                handleClean();
                Swal.fire({
                    title: 'Comentario realizada',
                    text: 'Has registrado un Comentario para esta aplicación!!!',
                    icon: 'success',
                });
            })
            .catch((err) => {
                console.log(err);
                setIsActive(false);
            });
    };

    // Clear form
    const handleClean = () => {
        setComentary({
            description: "",
            emisor: user.name,
            id_app: id_app
        });
    }

    return (
        <>
            <p>
                <a data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                    Nuevo comentario
                </a>
            </p>
            <div className="collapse" id="collapseExample">
                <div className="card card-body">
                    <form>
                        <div className="form-group row">
                            <label className="col-sm-3 col-form-label">Usuario</label>
                            <div className="col-sm-9">
                                <input type="text" readOnly className="form-control-plaintext" value={user.name} />
                            </div>
                        </div>
                        <div className="form-group row">
                            <label className="col-sm-3 col-form-label">Comentaio</label>
                            <div className="col-sm-9">
                                <textarea onChange={handleInputChange} value={description} className="form-control" id="description" name='description' required rows="3"></textarea>
                            </div>
                        </div>
                        <div className="form-group row">
                            <div className="col-sm-9">
                                <button type="button" maxLength={150} className="btn btn-primary btn-sm" name="description" id="description"
                                    onClick={saveComentary} disabled={isActive} >Guardar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <hr />
        </>
    )
}