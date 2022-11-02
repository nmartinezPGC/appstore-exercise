import { useState } from 'react';
import { ApplicationDetailContext } from './ApplicationDetailContext';

export const ApplicationDetailProvider = ({ children }) => {
    const [applicationDetail, setApplicationDetail] = useState({
        id_app: '',
        nombre_app: 'Prueba Apliccion',
        desarrollador_app: '',
        precio_app: 0,
        calificacion_app: 0,
        img_app: '',
        descripcion_app: '',
        comentarios: []
    });

    return (
        <ApplicationDetailContext.Provider value={{ applicationDetail, setApplicationDetail }}>
            {children}
        </ApplicationDetailContext.Provider>
    )
}
