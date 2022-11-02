import React, { useContext, useEffect, useState } from 'react';
import Swal from 'sweetalert2';
import { ApplicationDetailContext } from '../../context/ApplicationDetailContext';
import DownloadService from '../../service/DownloadService';
import { ComentaryApplication } from './ComentaryApplication';
import { ComentaryApplicationForm } from './ComentaryApplicationForm';
import { RatingApplication } from './RatingApplication';
import { SlideApplication } from './SlideApplication';

export const DetailApplication = () => {
    // State declarations
    const [enableInstall, setEnableInstall] = useState(false);
    const [countDownload, setCountDownload] = useState(0);

    // Context declaration
    const { applicationDetail: { id_app, nombre_app, desarrollador_app, precio_app, img_app, descripcion_app, instaled_app } }
        = useContext(ApplicationDetailContext);

    // Save Download / Install
    const saveDownload = (event) => {
        event.preventDefault();
        const downloadData = {
            nombre_app, id_app
        };
        DownloadService.newDownload({ downloadData })
            .then((transact) => {
                // handles
                if (enableInstall === false) {
                    setEnableInstall(true);
                } else {
                    setEnableInstall(false);
                }
                Swal.fire({
                    title: 'Descarga realizada',
                    text: 'Has descargado esta aplicación!!!',
                    icon: 'success',
                });
            })
            .catch((err) => {
                console.log(err);
            });
    };

    // Function All Downloads Applications
    const getCountDownloadApplication = async (application = 0) => {
        await DownloadService.getCountDownloadApplication(application)
            .then((resp) => {
                const { data } = resp;
                // Cambia el estado
                setCountDownload(data);
            })
            .catch((err) => {
                alert(err);
            });
    };

    useEffect(() => {
        if (id_app > 0) {
            getCountDownloadApplication(id_app);
        }
    }, [id_app])


    return (
        <>
            {/*  Modal  */}
            <div className="modal fade" id="modalApplication" tabIndex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div className="modal-dialog modal-dialog-centered" role="document">
                    <div className="modal-content">
                        <div className="modal-header">
                            <SlideApplication />
                        </div>
                        <div className="modal-body">
                            <div className='row'>
                                <div className="col-md-4">
                                    <img className="card-img-top"
                                        src={process.env.PUBLIC_URL + '/img/app-icons/' + img_app} alt={nombre_app} />
                                </div>
                                <div className="col-md-6 text-left">
                                    <h3 className="card-title pb-0">{nombre_app}</h3>
                                    <small>{desarrollador_app}</small>
                                    <p className="pb-0">{descripcion_app}</p>
                                    <h4 className="card-text font-weight-bold">$ {precio_app}</h4>
                                </div>
                                <div className="col-md-2">
                                    <i className="fa fa-download" aria-hidden="true">
                                        <small className='font-weight-bold ml-2'>{countDownload}</small>
                                    </i>
                                </div>
                            </div>
                            <hr />
                            {/* Starts section */}
                            <div className='d-flex align-items-center justify-content-center h-100'>
                                {/* Rating section */}
                                <RatingApplication />
                            </div>
                            <hr />

                            {/* Form comentary section */}
                            <ComentaryApplicationForm />

                            {/* Comentaries section */}
                            {id_app !== '' ?
                                <ComentaryApplication className="text-left" /> : ''
                            }

                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="button" className="btn btn-primary" onClick={saveDownload} disabled={instaled_app}>Install </button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}
