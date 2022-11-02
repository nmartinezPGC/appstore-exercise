import React, { useContext, useEffect, useState } from 'react';
import Paginator from 'react-hooks-paginator';
import ReactStars from "react-rating-stars-component";
import { Category } from '../../components/Category/Category';
import { ApplicationDetailContext } from '../../context/ApplicationDetailContext';
import ApplicationService from '../../service/ApplicationService';
import { DetailApplication } from '../detail-application/DetailApplication';
import './ApplicationListTable.css';

export const ApplicationListTable = () => {
    // States declarations
    const [applications, setApplications] = useState([]);
    const [category, setCategory] = useState(0);
    const pageLimit = 18;
    const [offset, setOffset] = useState(0);
    const [currentPage, setCurrentPage] = useState(1);
    const [currentData, setCurrentData] = useState([]);

    // Hooks de los contextos a usar
    const { setApplicationDetail } = useContext(ApplicationDetailContext);

    // Function All Applications
    const getAllApplications = async (category = 0) => {
        await ApplicationService.getAllApplications(category)
            .then((resp) => {
                const { data } = resp;
                const applications = data.map((app) => {
                    return {
                        id_app: app.id,
                        nombre_app: app.tittle,
                        desarrollador_app: app.developer,
                        precio_app: (app.price < 0.5 ? 'FREE' : app.price),
                        calificacion_app: app.rating,
                        img_app: app.img,
                        descripcion_app: app.description,
                        instaled_app: app.instaled,
                    };
                });
                // Cambia el estado
                setApplications(applications);
            })
            .catch((err) => {
                alert(err);
            });
    };

    // Get All Applications
    useEffect(() => {
        getAllApplications(category);
    }, [category]);

    // Filter categories
    const onSelectCategoryFilter = (onSelectCategory) => {
        setCategory(onSelectCategory);
    }

    useEffect(() => {
        setCurrentData(applications.slice(offset, offset + pageLimit));
    }, [offset, applications]);

    return (
        <>
            {/* Category Section */}
            <Category onSelectCategory={value => onSelectCategoryFilter(value)} />

            {/* Table list Applications */}
            <div className='row'>
                {currentData.map((app) => (
                    <div key={app.id_app} className='col-sm-6 col-md-3 col-lg-2 mb-3 cursor-pointer'
                        data-toggle="modal" data-target="#modalApplication"
                        onClick={() => setApplicationDetail({
                            id_app: app.id_app, nombre_app: app.nombre_app, desarrollador_app: app.desarrollador_app,
                            precio_app: app.precio_app, calificacion_app: app.calificacion_app, img_app: app.img_app,
                            descripcion_app: app.descripcion_app, instaled_app: app.instaled_app,
                        })}>
                        <div className="card target">
                            <img className="card-img-top"
                                src={process.env.PUBLIC_URL + '/img/app-icons/' + app.img_app} alt={app.nombre_app} />
                            <div className="card-body text-left">
                                <h4 className="card-title">{app.nombre_app}</h4>
                                <small>{app.desarrollador_app}</small>
                                <div className="row">
                                    <div className='col-md-12'>
                                        <ReactStars
                                            count={5}
                                            size={24}
                                            edit={false}
                                            value={app.calificacion_app}
                                            activeColor="#ffd700"
                                        />
                                    </div>
                                </div>
                                <p className="card-text font-weight-bold">$ {app.precio_app}</p>
                            </div>
                        </div>
                    </div>
                ))}
            </div>

            {/* Paginator of Applications */}
            <Paginator
                totalRecords={applications.length}
                pageLimit={pageLimit}
                pageNeighbours={2}
                setOffset={setOffset}
                currentPage={currentPage}
                setCurrentPage={setCurrentPage}
            />

            {/* Modal de Detalle */}
            <DetailApplication />
        </>
    )
}
