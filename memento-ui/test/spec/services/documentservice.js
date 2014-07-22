'use strict';

describe('Service: Documentservice', function () {

  // load the service's module
  beforeEach(module('mementoUiApp'));

  // instantiate service
  var Documentservice;
  beforeEach(inject(function (_Documentservice_) {
    Documentservice = _Documentservice_;
  }));

  it('should do something', function () {
    expect(!!Documentservice).toBe(true);
  });

});
