import React from 'react';
import {ComponentPreview, Previews} from '@react-buddy/ide-toolbox';
import {AppMenu} from "../app/AppMenu";
import {Home} from "../app/home/Home";
import App from "../app/App";

export const ComponentPreviews = () => {
  return (
    <Previews>

        <ComponentPreview path="/AppMenu">
            <AppMenu/>
        </ComponentPreview>
        <ComponentPreview path="/Home">
            <Home/>
        </ComponentPreview>
        <ComponentPreview path="/App">
            <App/>
        </ComponentPreview>
    </Previews>
  );
};
