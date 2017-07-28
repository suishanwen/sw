import {Pipe, PipeTransform} from '@angular/core';

// Tell Angular2 we're creating a Pipe with TypeScript decorators
@Pipe({
    name: 'SearchPipe'
})
export class SearchPipe implements PipeTransform {

    // Transform is the new "return function(value, args)" in Angular 1.x
    transform(value, args?) {
        let title = args;
        return value.filter(note => {
            return note.title.indexOf(title) !== -1;
        });
    }

}