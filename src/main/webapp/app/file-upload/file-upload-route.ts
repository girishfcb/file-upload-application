import { Route } from '@angular/router';

import { FileUploadComponent } from './file-upload.component';

export const FILE_UPLOAD_ROUTE: Route = {
  path: '',
  component: FileUploadComponent,
  data: {
    pageTitle: 'File Upload',
  },
};
