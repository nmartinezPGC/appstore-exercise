import React from 'react'
import { Footer } from '../../components/Footer/Footer'
import { ApplicationDetailProvider } from '../../context/ApplicationDetailProvider'
import { UserProvider } from '../../context/UserProvider'
import { ApplicationListTable } from './ApplicationListTable'

export const ApplicationScreen = () => {
    return (
        <>
            {/* Applications table list */}
            <UserProvider>
                <ApplicationDetailProvider>
                    <ApplicationListTable />
                </ApplicationDetailProvider>
            </UserProvider>

            <Footer />
        </>
    )
}
