import React from 'react';
import {ComponentPreview, Previews} from '@react-buddy/ide-toolbox';
import {AppMenu} from "../app/AppMenu";
import {Home} from "../app/home/Home";

export const ComponentPreviews = () => {
  return (
    <Previews>

        <ComponentPreview path="/AppMenu">
            <AppMenu/>
        </ComponentPreview>
        <ComponentPreview path="/Home">
            <Home/>
        </ComponentPreview>
    </Previews>
  );
};
